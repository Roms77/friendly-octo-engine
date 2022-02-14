package rmignac.authentication.exposition;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rmignac.authentication.domain.AppUser;
import rmignac.authentication.domain.UserCreationRequest;
import rmignac.authentication.domain.UserService;
import rmignac.authentication.exceptions.NotConnectedException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestParam String username, @RequestParam String password){

        userService.create(new UserCreationRequest(username, password));
        return ResponseEntity.ok().build();
    }

    //@PreAuthorize("hasRole('ADMIN') || hasRole('STAFF')")
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){


        return ResponseEntity.ok(userService.findAll().stream().map(appUser -> {
            UserDto user = new UserDto();
            user.setId(appUser.getId());
            user.setUsername(appUser.getUsername());
            user.setAuthorities(appUser.getAuthorities().stream()
                    .map(authority -> {
                        AuthorityDto authorityDto = new AuthorityDto();
                        authorityDto.setAuthority(authority.getAuthority());
                        authorityDto.setUuid(appUser.getId());
                        return authorityDto;
                    }).collect(Collectors.toList()));

            return user;
        }).collect(Collectors.toList()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> findById(){
        UserDto userDto = new UserDto();
        try{
            AppUser appUser= userService.findById();
            userDto.setUsername(appUser.getUsername());
            userDto.setId(appUser.getId());
            userDto.setAuthorities(appUser.getAuthorities().stream()
                    .map(authority -> {
                        AuthorityDto authorityDto = new AuthorityDto();
                        authorityDto.setAuthority(authority.getAuthority());
                        authorityDto.setUuid(appUser.getId());
                        return authorityDto;
                    }).collect(Collectors.toList()));

        }catch(NotConnectedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(userDto);

    }

    @PutMapping("/me/password")
    public ResponseEntity<UserDto> updatePassword(@RequestParam String newPassword){

        try{
            userService.updatePassword(newPassword);

        }catch(NotConnectedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/authority")
    public void updateAuthority(@PathVariable UUID id, @RequestParam String newAuthority){
        userService.updateAuthority(id, newAuthority);
    }


}
