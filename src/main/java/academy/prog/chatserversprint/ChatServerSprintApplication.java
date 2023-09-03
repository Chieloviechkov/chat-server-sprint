package academy.prog.chatserversprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//EnableWebSecurity
public class ChatServerSprintApplication {

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    //auth.requestMatchers("/test").hasRole("ADMIN");
                    auth.anyRequest().permitAll();
                })
                .httpBasic(withDefaults());

        return http.build();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ChatServerSprintApplication.class, args);
    }
}
