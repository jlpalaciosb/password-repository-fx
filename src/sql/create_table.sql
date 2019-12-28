

CREATE TABLE `User` (
 `UserID` int(11) NOT NULL AUTO_INCREMENT,
 `Name` varchar(100) NOT NULL,
 `PasswordSHA256` varbinary(255) NOT NULL,
 PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8


CREATE TABLE `Password` (
 `PasswordID` int(11) NOT NULL AUTO_INCREMENT,
 `UserID` int(11) NOT NULL,
 `Site` varchar(64) NOT NULL,
 `Identity` varchar(64) NOT NULL,
 `PasswordAES` varbinary(255) NOT NULL,
 PRIMARY KEY (`PasswordID`),
 KEY `abc` (`UserID`),
 CONSTRAINT `abc` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8
