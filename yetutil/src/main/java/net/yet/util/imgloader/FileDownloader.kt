package net.yet.util.imgloader

import net.yet.file.SdAppFile
import net.yet.net.Http
import net.yet.util.MultiHashMap
import net.yet.util.database.DBMap
import net.yet.util.fore
import net.yet.util.log.xlog
import java.io.File
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by entaoyang@163.com on 2016-11-02.
 */

object FileDownloader {
	//url->file
	private val map = DBMap.tab("file_downloader")
	//下载中的文件
	private val processSet = HashSet<String>()

	private val listenMap = MultiHashMap<String, DownListener>()

	private val es: ExecutorService = Executors.newFixedThreadPool(4) { r ->
		val t = Thread(r)
		t.isDaemon = false
		t.priority = Thread.NORM_PRIORITY - 1
		t.setUncaughtExceptionHandler { t, e ->
			e.printStackTrace()
			xlog.e(e)
			xlog.flush()
		}
		t
	}

	fun backRun(callback: () -> Unit) {
		es.submit(callback)
	}

	//查找本地
	fun findLocal(url: String): File? {
		val f = map[url] ?: return null
		val file = File(f)
		if (file.exists()) {
			return file
		}
		map.remove(url)
		return null
	}

	fun removeLocal(url: String) {
		val f = map[url] ?: return
		File(f).delete()
		map.remove(url)
	}

	fun isDownloading(url: String): Boolean {
		return url in processSet
	}

	fun download(url: String, block: (File?) -> Unit) {
		backRun {
			downloadSync(url, object : DownListener {
				override fun onDownload(url: String, ok: Boolean) {
					val f = findLocal(url)
					if (f != null) {
						block(f)
					} else {
						block(null)
					}
				}

			})
		}
	}

	fun downloadSync(url: String, listener: DownListener? = null) {
		if (listener != null) {
			synchronized(listenMap) {
				listenMap.put(url, listener)
			}
		}
		if (!processSet.add(url)) {
			return
		}
		val tmp = SdAppFile.tempFile()
		val r = Http(url).download(tmp, null)
		val ok = r.OK && r.contentLength > 0 && tmp.length() == r.contentLength.toLong()
		if (ok) {
			map[url] = tmp.absolutePath
		} else {
			tmp.delete()
		}
		processSet.remove(url)
		fore {
			val ls = synchronized(listenMap) {
				listenMap.remove(url)
			}
			if (ls != null) {
				for (l in ls) {
					l.onDownload(url, ok)
				}
			}
		}
	}

	fun retrive(url: String, block: (File?) -> Unit) {
		val f = findLocal(url)
		if (f != null) {
			block(f)
			return
		}
		download(url, block)
	}
}