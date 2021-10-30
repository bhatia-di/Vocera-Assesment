import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVConsumerMain {

    private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) {
        System.out.println("Begin Extraction");
        Map<String, Person> output = new HashMap<String, Person>();


        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Projects\\Vocera-Assesment\\resources\\DataInput.csv"))) {
            String row;
            while ((row = br.readLine()) != null) {
                String[] values = row.split(COMMA_DELIMITER);
                String name = values[values.length-3].contains(":") ? null : values[values.length-3];
                String packet = values[values.length-2].replaceAll("[\\D]", "");
                Integer numberOfPackets = packet.isEmpty() ? null : Integer.parseInt(packet);
                String sessionId = values[values.length-1];
                Person per = Objects.isNull(output.get(sessionId)) ? new Person(): output.get(sessionId);

                if (!Objects.isNull(name)) {
                    per.setName(name);
                }

                if (!Objects.isNull(numberOfPackets)) {
                    per.setNumberOfPackets(numberOfPackets);
                }
                output.put(sessionId, per);
            }



            // processing output
            Map<String, Integer> personToPacketMapper = new HashMap<String, Integer>();
            Person maxPerson = new Person();
            maxPerson.setName(null);
            maxPerson.setNumberOfPackets(0);

            for (Map.Entry<String, Person> entry: output.entrySet()) {
                int packetCount;
                String packetDelivererName = entry.getValue().getName();
                if (Objects.isNull(packetDelivererName)) continue;


                else if (personToPacketMapper.containsKey(entry.getValue().getName())) {
                    packetCount = entry.getValue().getNumberOfPackets() + personToPacketMapper.get(entry.getValue().getName());
                    personToPacketMapper.put(packetDelivererName, packetCount );

                } else {
                    packetCount = entry.getValue().getNumberOfPackets();
                    personToPacketMapper.put(packetDelivererName, packetCount);
                }

                if (maxPerson.getNumberOfPackets() < packetCount) {
                    maxPerson.setNumberOfPackets(packetCount);
                    maxPerson.setName(packetDelivererName);
                }

            }


            System.out.println(maxPerson.getName() + " has dropped " + maxPerson.getNumberOfPackets() + " packets");





        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}