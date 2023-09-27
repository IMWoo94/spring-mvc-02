package hello.itemservice.domain.item;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
// @ScriptAssert(lang = "javascript", script = "_this.price * _this.qunantity >= 10000", message = "총합이 10000원 넘게 해주세요.")
public class Item {

	private Long id;
	@NotBlank(message = "공백일 수 없습니다.")
	private String itemName;
	@NotNull
	@Range(min = 1000, max = 1000000)
	private Integer price;
	@NotNull
	@Max(9999)
	private Integer quantity;

	public Item() {
	}

	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
