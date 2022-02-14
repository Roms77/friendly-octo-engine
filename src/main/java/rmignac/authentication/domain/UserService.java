package rmignac.authentication.domain;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rmignac.authentication.exceptions.NotConnectedException;
import rmignac.authentication.exposition.AuthorityDto;
import rmignac.authentication.infrastructure.AuthorityEntity;
import rmignac.authentication.infrastructure.AuthorityId;
import rmignac.authentication.infrastructure.UserDao;
import rmignac.authentication.infrastructure.UserEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void create(UserCreationRequest userCreationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreationRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        UserEntity savedUser = userDao.save(userEntity);

        List<AuthorityEntity> authorities = new ArrayList<>();
        authorities.add(new AuthorityEntity(AuthorityId.getDefaultAuthority(savedUser.getId())));
        savedUser.setAuthorities(authorities);

        userDao.save(savedUser);
    }

    public void updateAuthority(UUID id, String newAuthority){
        UserEntity user = userDao.getById(id);
        if(user == null) return;

        List<AuthorityEntity> authorities = user.getAuthorities();
        AuthorityEntity authority = new AuthorityEntity();
        AuthorityId authorityId = new AuthorityId();

        if(authorityId.getAuthority().contains(newAuthority)) return;

        authorityId.setAuthority(newAuthority);
        authorityId.setUuid(id);
        authority.setAuthorityId(authorityId);
        authorities.add(authority);
        user.setAuthorities(authorities);
        userDao.save(user);
    }
    public void updatePassword(String newPassword) throws NotConnectedException{
        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (credentials == null) throw new NotConnectedException();
        UserEntity user = new UserEntity();
        user.setId(credentials.getId());
        user.setUsername(credentials.getUsername());

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setAuthorities(credentials.getAuthorities().stream()
                .map(authority -> {
                    AuthorityEntity authorityEntity = new AuthorityEntity();
                    AuthorityId authorityId = new AuthorityId();
                    authorityId.setAuthority(authority.getAuthority());
                    authorityId.setUuid(credentials.getId());
                    authorityEntity.setAuthorityId(authorityId);
                    return authorityEntity;
                }).collect(Collectors.toList()));
        userDao.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(s);
        List<String> authoritiesList = userDao.findAuthorityById(user.getId());
        String authorities = String.join(",", authoritiesList);
        return new AppUser(user.getId(), user.getUsername(), user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }


    public List<AppUser> findAll(){
        List<AppUser> listeUser = new ArrayList<>();

        return userDao.findAll().stream().map(userEntity -> {
            List<String> authoritiesList = userDao.findAuthorityById(userEntity.getId());
            String authorities = String.join(",", authoritiesList);
            return new AppUser(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        }).collect(Collectors.toList());
    }

    public AppUser findById() throws NotConnectedException{

        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (credentials == null) throw new NotConnectedException();
        return credentials;
    }
}
