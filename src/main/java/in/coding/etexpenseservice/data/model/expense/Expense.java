package in.coding.etexpenseservice.data.model.expense;


import in.coding.etexpenseservice.data.model.expCat.ExpCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(
        name = "expId_userId",
        def = "{'expId': 1, 'userId': 1}",
        unique = true
)
@Document(collection = "expenses")
public class Expense {
    @Id
    private String expId;
    private String userId;
    private String title;
    private Integer amount;

    @DocumentReference
    private ExpCategory expCat;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

}
