/**
 * 
 */
package com.imooc.web.async;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟消息队列
 * @author zhailiang
 *
 */
@Component
@Slf4j
public class MockQueue {

	@Getter private String placeOrder;

	@Setter@Getter private String completeOrder;
	
	public void setPlaceOrder(String placeOrder){
		new Thread(() -> {
			log.info("接到下单请求, " + placeOrder);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
            log.info("下单请求处理完毕," + placeOrder);
		}).start();
	}
}
