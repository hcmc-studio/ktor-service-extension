package studio.hcmc.ktor.service

interface IdProducerService<ID> {
    suspend fun onIdAdded(id: ID)

    suspend fun onIdRemoved(id: ID)
}