package in.coding.etexpenseservice.controller.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpCategory {
    private String userId;
    private String name;
}
