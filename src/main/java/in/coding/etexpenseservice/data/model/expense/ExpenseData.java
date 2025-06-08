package in.coding.etexpenseservice.data.model.expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseData {
    private String title;
    private Long amount;
    private String expCatId;
}
