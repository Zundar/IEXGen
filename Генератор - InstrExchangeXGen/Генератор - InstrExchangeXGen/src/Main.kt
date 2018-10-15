import me.tongfei.progressbar.ProgressBar
import org.pmw.tinylog.Logger
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    val path = Paths.get("settings.property")
    var settingsStream: BufferedReader
    if (Files.exists(path)) {
        settingsStream = Files.newBufferedReader(path)
    } else {
        settingsStream = BufferedReader(InputStreamReader(Settings::class.java.getResourceAsStream("settings.property")))
    }
    val properties = Properties()
    properties.load(settingsStream)
    val settings = Settings(
            threadPoolSize = properties.getProperty("threadPoolSize", "4").toInt(),
            logFile = URI(properties.getProperty("logFile", "./IEXlogs.txt")),
            numberOfFiles = properties.getProperty("numberOfFiles", "100").toInt(),
            numberOfRowsPerFiles = properties.getProperty("numberOfRowsPerFiles", "100").toInt(),
            isDebug = properties.getProperty("isDebug", "false")!!.toBoolean(),
            minimalVolume = properties.getProperty("minimalVolume", "1").toFloat(),
            maximumVolume = properties.getProperty("maximumVolume", "1").toFloat(),
            fileMask = properties.getProperty("fileMask", "NT_IEXFile_<FILENUMBER>"),
            fillZero = properties.getProperty("fillZero", "6").toInt(),
            expireDay = properties.getProperty("expireDay", "0").toInt(),
            action = properties.getProperty("action", null).toInt(),
            quantity = properties.getProperty("quantity", null).toInt(),
            firm = properties.getProperty("firm", null),
            extID = properties.getProperty("extID", null),
            classCode = properties.getProperty("classCode", null),
            clientCode = properties.getProperty("clientCode", null),
            account = properties.getProperty("account", null),
            clientCodeCP = properties.getProperty("clientCodeCP", null),
            realClass = properties.getProperty("realClass", null),
            realFirm = properties.getProperty("realFirm", null),
            realSecCode = properties.getProperty("realSecCode", null),
            resultDataFolder = properties.getProperty("resultDataFolder", null),
            seccode = properties.getProperty("seccode", null),
            secCodeCP = properties.getProperty("secCodeCP", null),
            tradeSystem = properties.getProperty("tradeSystem", null)
    )
    Logger.info("Запуск InstrExchangeXGenerator...")
    Logger.info("settings = $settings")
    val progressBar = ProgressBar("Генерация файлов",
            (settings.numberOfFiles * settings.numberOfRowsPerFiles + settings.numberOfFiles).toLong())

    Logger.info("Создание каталога результатов ${settings.resultDataFolder}")
    val dateFormat = SimpleDateFormat("yyyyMMdd_hhmmss")
    val folderPath = settings.resultDataFolder.replace("<DATETIME>", dateFormat.format(Date()))
    File(folderPath).mkdir()

    Logger.info("Генерация документов...")
    val executor = Executors.newFixedThreadPool(settings.threadPoolSize)
    for (docNumber in 1..settings.numberOfFiles) {
        val fileNumber = String.format("%0${settings.fillZero}d", docNumber)
        val fileName = settings.fileMask.replace("<FILENUMBER>", fileNumber)
        Logger.debug("fileNumber = ${fileNumber} fileName = ${fileName}")
        executor.execute(InstrExchangeXDocument(fileName, folderPath, settings, progressBar))
    }
    executor.shutdown()
    while (!executor.isTerminated) {
    }
    progressBar.stop()
    Logger.info("Генерация документов завершенна")
}