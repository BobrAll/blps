package bobr.blps_lab.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {
    @Value("${RABBITMQ_HOST}")
    private String rabbitmqHost;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[]{rabbitmqHost});
        options.setUserName("guest");
        options.setPassword("guest".toCharArray());

        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler("javaTestClient", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("queue");
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }

    /*
        @Bean
        public MessageChannel mqttOutboundChannel() {
            return new DirectChannel();
        }

        @Bean
        public IntegrationFlow mqttInFlow() {
            return IntegrationFlow.from(mqttInbound())
                    .transform(p -> p + ", received from MQTT")
                    .handle(message -> System.out.println("MQTT Message received: " + message))
                    .get();
        }

        @Bean
        public MessageProducerSupport mqttInbound() {
            MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("javaMqqtClient",
                    mqttClientFactory(), "#");
            adapter.setCompletionTimeout(5000);
            adapter.setConverter(new DefaultPahoMessageConverter());
            adapter.setQos(1);
            return adapter;
        }
    */
    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MQTTGateway {
        void sendToMqtt(String data);
    }
}
