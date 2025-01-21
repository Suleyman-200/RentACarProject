    package com.example.rentacar.business.concretes;

    import com.example.rentacar.business.abstracts.UserService;
    import com.example.rentacar.dto.request.CreateUserRequest;
    import com.example.rentacar.dto.request.UpdateUserRequest;
    import com.example.rentacar.dto.responses.GetAllUserResponse;
    import com.example.rentacar.dto.responses.GetByIdUserResponse;
    import com.example.rentacar.business.rules.UserBusinessRules;
    import com.example.rentacar.exceptions.NotFoundException;
    import com.example.rentacar.repositories.UserRepository;
    import com.example.rentacar.models.User;
    import com.example.rentacar.mappers.ModelMapperService;

    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.List;


    @Service
    public class
    UserManager implements UserService {
        private final ModelMapperService modelMapperService;
        private final UserRepository userRepository;
        private final UserBusinessRules userBusinessRules;
        private final PasswordEncoder passwordEncoder;
        public UserManager(ModelMapperService modelMapperService, UserRepository userRepository, UserBusinessRules userBusinessRules, PasswordEncoder passwordEncoder) {
            this.modelMapperService = modelMapperService;
            this.userRepository = userRepository;
            this.userBusinessRules = userBusinessRules;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public List<GetAllUserResponse> getAll() {
           List<User> users= userRepository.findAll();

           return users.stream().map(user ->this.modelMapperService.forResponse().map(user, GetAllUserResponse.class)).toList();
        }

        @Override
        public GetByIdUserResponse getById(Long id) {
            User customers=this.userRepository.findById(id).orElseThrow();

            return modelMapperService.forResponse().map(customers, GetByIdUserResponse.class);
        }

        @Override
        public void add(CreateUserRequest createUserRequest) {
            User customer=this.modelMapperService.forRequest().map(createUserRequest, User.class);
            this.userBusinessRules.checkIfexistsCustomerByEmail(createUserRequest.geteMail());
            this.userBusinessRules.checkIfexistsCustomerByNumber(createUserRequest.getPhone());
            this.userBusinessRules.isAgeAllowed(createUserRequest.getDateOfBirth());
            this.userRepository.save(customer);
        }

        @Override
        public void update(UpdateUserRequest updateUserRequest) {
            User customer=this.modelMapperService.forRequest().map(updateUserRequest, User.class);
            User customer1= userRepository.findById(updateUserRequest.getId()).orElseThrow(()-> new NotFoundException("Couldn't not find id"+customer.getId()));
            this.userBusinessRules.checkIfexistsCustomerByEmail(updateUserRequest.getEmail());
            this.userBusinessRules.checkIfexistsCustomerByNumber(updateUserRequest.getPhone());
            customer1.setEmail(updateUserRequest.getEmail());
            customer1.setPhone(updateUserRequest.getPhone());
            this.userRepository.save(customer1);
        }





        @Override
        public void delete(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new NotFoundException("Couldn't not find id"+id));
        this.userRepository.delete(user);
        }

    }
