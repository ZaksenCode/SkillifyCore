package me.zaksen.skillify_core.database

import me.zaksen.skillify_core.api.database.RepositorySpecification
import me.zaksen.skillify_core.api.database.SqlSpecification
import me.zaksen.skillify_core.database.dao.PlayerProfile

interface PlayerProfileSpecification: RepositorySpecification<PlayerProfile>, SqlSpecification