package studio.hcmc.ktor.service

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import studio.hcmc.ktor.service.IdProducer
import java.util.concurrent.Future

class IdProducer<ID, V>(
    private val producer: KafkaProducer<String, V>,
    private val topic: String,
    private val insertionKey: String,
    private val deletionKey: String,
    private val idMapper: (id: ID) -> V
) : IdProducerService.Delegate<ID> {
    private fun send(id: ID, key: String): Future<RecordMetadata> {
        return producer.send(ProducerRecord(
            /* topic = */ topic,
            /* partition = */ null,
            /* timestamp = */ System.currentTimeMillis(),
            /* key = */ key,
            /* value = */ idMapper(id),
            /* headers = */ null
        ))
    }

    override suspend fun onIdAdded(id: ID) {
        send(id, insertionKey)
    }

    override suspend fun onIdRemoved(id: ID) {
        send(id, deletionKey)
    }
}