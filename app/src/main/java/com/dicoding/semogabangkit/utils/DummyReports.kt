package com.dicoding.semogabangkit.utils

import com.dicoding.semogabangkit.R
import com.dicoding.semogabangkit.data.entity.ReportEntity

object DummyReports {

    fun generate(): List<ReportEntity> {

        val reports = ArrayList<ReportEntity>()

        reports.addAll(arrayListOf(
            ReportEntity(1,
                "Jalan Rusak di Keputih",
                "Pembangunan",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "http://semogabangkit.topanlabs.com/poto/dragon.jpeg",
                10,
                "Surabaya"),

            ReportEntity(2,
                "Bantuan Sosial",
                "Sosial",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "http://semogabangkit.topanlabs.com/poto/dragon.jpeg",
                25,
                "Surabaya"),

            ReportEntity(3,
                "Antrian Panjang di Puskesmas",
                "Kesehatan",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "http://semogabangkit.topanlabs.com/poto/dragon.jpeg",
                33,
                "Surabaya"),

            ReportEntity(4,
                "Harga Ikan Terlalu Murah",
                "Ekonomi",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "http://semogabangkit.topanlabs.com/poto/dragon.jpeg",
                47,
                "Surabaya"),

            ReportEntity(5,
                "Angkot Keputih Terlalu Sedikit",
                "Transportasi",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "http://semogabangkit.topanlabs.com/poto/dragon.jpeg",
                54,
                "Surabaya")
            ))

        return reports
    }

}