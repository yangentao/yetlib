package yet.net

private val codeMap = hashMapOf(
		100 to "Continue",
		101 to "Switching Protocols",
		102 to "Processing",
		200 to "OK",
		201 to "Created",
		202 to "Accepted",
		203 to "Non-Authoritative Information",
		204 to "No Content",
		205 to "Reset Content",
		206 to "Partial Content",
		207 to "Multi-Status",
		300 to "Multiple Choices",
		301 to "Moved Permanently",
		302 to "Move temporarily",
		303 to "See Other",
		304 to "Not Modified",
		305 to "Use Proxy",
		306 to "Switch Proxy",
		307 to "Temporary Redirect",
		400 to "Bad Request",
		401 to "Unauthorized",
		402 to "Payment Required",
		403 to "Forbidden",
		404 to "Not Found",
		405 to "Method Not Allowed",
		406 to "Not Acceptable",
		407 to "Proxy Authentication Required",
		408 to "Request Timeout",
		409 to "Conflict",
		410 to "Gone",
		411 to "Length Required",
		412 to "Precondition Failed",
		413 to "Request Entity Too Large",
		414 to "Request-URI Too Long",
		415 to "Unsupported Media Type",
		416 to "Requested Range Not Satisfiable",
		417 to "Expectation Failed",
		421 to "too many connections",
		422 to "Unprocessable Entity",
		423 to "Locked",
		424 to "Failed Dependency",
		425 to "Unordered Collection",
		426 to "Upgrade Required",
		449 to "Retry With",
		451 to "Unavailable For Legal Reasons",
		500 to "Internal Server Error",
		501 to "Not Implemented",
		502 to "Bad Gateway",
		503 to "Service Unavailable",
		504 to "Gateway Timeout",
		505 to "HTTP Version Not Supported",
		506 to "Variant Also Negotiates",
		507 to "Insufficient Storage",
		509 to "Bandwidth Limit Exceeded",
		510 to "Not Extended",
		600 to "Unparseable Response Headers"
)

fun httpMsgByCode(code: Int): String? {
	return codeMap[code]
}