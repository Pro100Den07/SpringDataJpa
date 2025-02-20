package org.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findUsersByDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    public List<Post> findPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
