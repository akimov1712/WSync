package ru.topbun.wsync.utils

import java.util.Calendar
import java.util.Date


fun Long.toDate() = Date(this * 1000)