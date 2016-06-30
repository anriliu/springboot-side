package com.github.yingzhuo.springboot.side.kotlin.range

import com.github.yingzhuo.springboot.side.kotlin.addDays
import com.github.yingzhuo.springboot.side.kotlin.truncate
import java.util.*

class DateRange(start: Date, end: Date, val function: (Date) -> Date = { it.truncate() }): ClosedRange<Date>, Iterable<Date> {

    override val start: Date = function(start)
    override val endInclusive: Date = function(end)

    override fun iterator(): Iterator<Date> = DateRangeIterator(this)

    private inner class DateRangeIterator(val dateRange: DateRange) : Iterator<Date> {
        private var current: Date = dateRange.start

        override fun hasNext(): Boolean = dateRange.function(current) <= dateRange.function(dateRange.endInclusive)

        override fun next(): Date {
            val plus = dateRange.function(current.addDays())
            try {
                return current
            } finally {
                current = plus
            }
        }
    }
}
