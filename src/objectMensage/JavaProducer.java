package objectMensage;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JavaProducer implements Runnable {

    public void run() {
        try { // Create a connection factory.
            ActiveMQConnectionFactory factory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");

            //Create connection.
            Connection connection = factory.createConnection();


            // Start the connection
            connection.start();

            // Create a session which is non transactional
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create Destination queue
            Destination queue = session.createQueue("Queue");

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String msg = "Aula Pos-Unipe, Arquitetura Software Web, 17/10/2020";

            // insert message
            //TextMessage message = session.createTextMessage(msg);
            //System.out.println("Producer Sent: " + msg);
            //producer.send(message);

            //insert object mensagem
            Paciente paciente = new Paciente();
            paciente.setID(1);
            paciente.setName("victor");
            ObjectMessage objetoMensagem = session.createObjectMessage(paciente);
            System.out.println("Producer Sent objectMsg: "+ msg + objetoMensagem );
            producer.send(objetoMensagem);

            session.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Exception Occured");
        }
    }
}

