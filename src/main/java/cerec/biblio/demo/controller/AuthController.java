package cerec.biblio.demo.controller;


import cerec.biblio.demo.dao.*;
import cerec.biblio.demo.dto.UserResponse;

import cerec.biblio.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  /*@Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  private AccountService accountService;

  public AuthController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getEmail(), roles));
  }

  @PostMapping("/register")
  public List<UserResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    System.out.println(signUpRequest.toString());

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new RuntimeException("Error: Username is already taken!");
    }

    *//*if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new RuntimeException("Error: Email is already in use!");
    }*//*

    // Create new user's account
    User user = new User();

    user.setUsername(signUpRequest.getUsername());
    user.setNom(signUpRequest.getNom());
    user.setPrenom(signUpRequest.getPrenom());
    user.setEmail(signUpRequest.getUsername());
    String password="123456";
    user.setPassword(encoder.encode(password));
    user.setEnabled(true);

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.LECTEUR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    }

    user.setRoles(roles);
    userRepository.save(user);

    return accountService.getAllUsers();
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    System.out.println("Le token rafraichit: "+request);

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getEmail());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
  
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
    refreshTokenService.deleteByUserId(logOutRequest.getUserId());
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

  @GetMapping("/user")
  public UserResponse getUserByEmail(){
    return accountService.getUtilisateurByEmail();
  }

  @GetMapping("/get-users-by-id/{id}")
  public UserResponse getUserById(@PathVariable("id") Long id){
    return accountService.getUtilisateurById(id);
  }

  @GetMapping("/users-counter")
  public Integer compteurUsers(){
    return accountService.compterUsers();
  }

  @PostMapping("/update-user-account")
  public UserResponse updateUserAccount(@RequestBody LoginRequest loginRequest){
    return accountService.updateUserAccount(loginRequest);
  }

  @DeleteMapping("/delete-user-account/{idUser}")
  public List<UserResponse> deleteUserAccount(@PathVariable("idUser") Long idUser){
    return accountService.deleteUserAccount(idUser);
  }

  @GetMapping("/get-roles")
  public List<RoleResponse> getRoles(){
    return accountService.getRoles();
  }

  @PostMapping("/add-role-to-user")
  public List<UserResponse> addRoleToUser(@RequestBody UserModified userModified){
    return accountService.addRoleToUser(userModified);
  }

  @PostMapping("/delete-role-to-user")
  public List<UserResponse> deleteRoleToUser(@RequestBody UserModified userModified){
    return accountService.deleteRoleToUser(userModified);
  }


  @PostMapping("/edit-user")
  public List<UserResponse> editUser(@RequestBody EditRequest editRequest){
    return accountService.editUser(editRequest);
  }


  @GetMapping("/bloque-compte/{id}")
  public List<UserResponse> bloqueCompte(@PathVariable("id") Long idUtilisateur){
    return accountService.bloqueCompte(idUtilisateur);
  }

  @GetMapping("/debloque-compte/{id}")
  public List<UserResponse> deBloqueCompte(@PathVariable("id") Long idUtilisateur){
    return accountService.deBloqueCompte(idUtilisateur);
  }*/

}
