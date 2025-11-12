-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 09, 2025 at 05:15 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hukum`
--

-- --------------------------------------------------------

--
-- Table structure for table `konsultan`
--

CREATE TABLE `konsultan` (
  `NIP_Konsultan` varchar(20) NOT NULL,
  `Telp` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL,
  `Nama` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `konsultasi_dan_layanan_hukum`
--

CREATE TABLE `konsultasi_dan_layanan_hukum` (
  `ID_Layanan` varchar(20) NOT NULL,
  `ID_Jadwal` varchar(20) DEFAULT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `NIP_konsultan` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `ID_Pelanggan` varchar(20) NOT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `ID_Pembayaran` varchar(20) NOT NULL,
  `ID_Layanan` varchar(20) DEFAULT NULL,
  `ID_Pelanggan` varchar(20) DEFAULT NULL,
  `Nama` varchar(100) DEFAULT NULL,
  `Report_Pembayaran` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `konsultan`
--
ALTER TABLE `konsultan`
  ADD PRIMARY KEY (`NIP_Konsultan`);

--
-- Indexes for table `konsultasi_dan_layanan_hukum`
--
ALTER TABLE `konsultasi_dan_layanan_hukum`
  ADD PRIMARY KEY (`ID_Layanan`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`ID_Pelanggan`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`ID_Pembayaran`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
