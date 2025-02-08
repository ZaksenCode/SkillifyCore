package me.zaksen.skillify_core.api.config

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import me.zaksen.skillify_core.api.config.serialization.ItemStackValue
import me.zaksen.skillify_core.api.data.loot.entry.ItemStackEntry
import me.zaksen.skillify_core.api.data.loot.entry.LivingEntityEntry
import me.zaksen.skillify_core.api.data.loot.entry.LootTableEntry
import me.zaksen.skillify_core.api.data.recipe.CookingRecipe
import me.zaksen.skillify_core.api.data.recipe.Recipe
import me.zaksen.skillify_core.api.data.recipe.ShapedRecipe
import me.zaksen.skillify_core.api.data.recipe.ShapelessRecipe
import org.bukkit.entity.LivingEntity
import java.io.File

val modules = SerializersModule {
    polymorphic(Recipe::class) {
        subclass(ShapedRecipe::class)
        subclass(ShapelessRecipe::class)
        subclass(CookingRecipe::class)
    }
    polymorphic(LootTableEntry::class) {
        subclass(ItemStackEntry::class)
        subclass(LivingEntityEntry::class)
    }
}

val yaml by lazy {
    Yaml(
        configuration = YamlConfiguration(
            strictMode = false
        ),
        serializersModule = modules
    )
}

fun loadFile(folder: File, fileName: String): File {
    return try {
        folder.mkdirs()
        val file = File(folder, fileName)
        if (!file.exists()) file.createNewFile()
        file
    } catch (e: Throwable) {
        throw RuntimeException(e)
    }
}

inline fun <reified T> loadConfig(configFile: File, yml: Yaml = yaml): T {
    if (configFile.length() == 0L) {
        val config = T::class.java.getDeclaredConstructor().newInstance()
        saveConfig(File(configFile.absolutePath.replace("/${configFile.name}", "")), configFile.name, config)
        return config
    }

    return yml.decodeFromString<T>(configFile.readText())
}

inline fun <reified T> loadConfig(folder: File, configName: String, yml: Yaml = yaml): T {
    val file = loadFile(folder, configName)

    if (file.length() == 0L) {
        val config = T::class.java.getDeclaredConstructor().newInstance()
        saveConfig(folder, configName, config)
        return config
    }

    return yml.decodeFromString<T>(file.readText())
}

inline fun <reified T> saveConfig(dataFolder: File, name: String, value: T, yml: Yaml = yaml) {
    val text = yml.encodeToString<T>(value)
    loadFile(dataFolder, name).writeText(text)
}