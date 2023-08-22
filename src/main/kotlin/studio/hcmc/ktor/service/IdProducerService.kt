package studio.hcmc.ktor.service

interface IdProducerService<ID> {
    val delegate: Delegate<ID>

    interface Delegate<ID> {
        suspend fun onIdAdded(id: ID)

        suspend fun onIdRemoved(id: ID)

        class Empty<ID> : Delegate<ID> {
            override suspend fun onIdAdded(id: ID) = Unit

            override suspend fun onIdRemoved(id: ID) = Unit
        }
    }
}