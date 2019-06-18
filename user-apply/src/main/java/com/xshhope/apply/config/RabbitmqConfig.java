package com.xshhope.apply.config;

import com.xshhope.model.apply.constants.UserApplyMq;
import com.xshhope.model.user.constants.UserCenterMq;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 * 
 * @author xshhope
 *
 */
@Configuration
public class RabbitmqConfig {

	/**
	 * 申请更新列名
	 */
	public static final String APPLY_UPDATE_QUEUE = "apply.update.queue";

	/**
	 * 声明队列，此队列用来接收申请更新的消息
	 *
	 * @return
	 */
	@Bean
	public Queue applyUpdateQueue() {
		Queue queue = new Queue(APPLY_UPDATE_QUEUE);

		return queue;
	}

	@Bean
	public TopicExchange userTopicExchange() {
		return new TopicExchange(UserApplyMq.MQ_EXCHANGE_Apply);
	}

	/**
	 * 将申请更新队列和用户的exchange做个绑定
	 *
	 * @return
	 */
	@Bean
	public Binding bindingRoleDelete() {
		Binding binding = BindingBuilder.bind(applyUpdateQueue()).to(userTopicExchange())
				.with(UserApplyMq.ROUTING_KEY_APPLY_UPDATE);
		return binding;
	}
}
