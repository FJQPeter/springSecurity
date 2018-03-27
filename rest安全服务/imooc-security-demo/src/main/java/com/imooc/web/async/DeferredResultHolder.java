/**
 * 
 */
package com.imooc.web.async;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zhailiang
 *
 */
@Component
public class DeferredResultHolder {
	
	@Setter@Getter private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

}
