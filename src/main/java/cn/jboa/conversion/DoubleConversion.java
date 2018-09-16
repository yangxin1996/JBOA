package cn.jboa.conversion;

import java.math.BigDecimal;
import java.util.Map;
import ognl.DefaultTypeConverter;

public class DoubleConversion extends DefaultTypeConverter{
	public Object convertValue(Map<?, ?> context, String[] values, Class<?> toClass) {
		if(toClass != Double.class||values == null || values.length==0){
			return null;
		}
		String score = values[0];
		Double value =new BigDecimal(score).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}
}
