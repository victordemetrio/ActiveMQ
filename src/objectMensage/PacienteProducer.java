package objectMensage;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PacienteProducer implements Runnable {

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
            Destination queue = session.createQueue("br.paciente");

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            Paciente p = new Paciente();
            p.setId(1);
            p.setName("Victor Demetrio");

            // insert message
            ObjectMessage message = session.createObjectMessage(p);

            System.out.println("Paciente message enviado: " + p.getId());
            producer.send(message);

            session.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Exception Occured" );
            ex.printStackTrace();
        }
    }

}