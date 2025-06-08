package in.coding.etexpenseservice.mapper;

import in.coding.etexpenseservice.controller.dto.ExpenseRequest;
import in.coding.etexpenseservice.data.model.expense.ExpenseData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    ExpenseData mapToExpense(ExpenseRequest expenseRequest);
}
