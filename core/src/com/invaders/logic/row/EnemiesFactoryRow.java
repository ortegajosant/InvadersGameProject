package com.invaders.logic.row;

public class EnemiesFactoryRow {

	private static EnemiesFactoryRow instance;

	private EnemiesFactoryRow() {

	}

	public static EnemiesFactoryRow getInstance() {
		if (instance == null) {
			instance = new EnemiesFactoryRow();
		}
		return instance;
	}

	public AbstractEnemyRow createEnemyRow(int level, String rowType) {
		switch (level) {
		case 1:
			return new BasicRow(70);
		case 2:
			if (rowType == "Basic") {
				return new BasicRow(75);
			} else {
				return new ClassARow(75);
			}
		case 3:
			if (rowType == "Basic") {
				return new BasicRow(80);
			} else if (rowType == "Class A") {
				return new ClassARow(80);
			} else {
				return new ClassBRow(80);
			}
		case 4:
			if (rowType == "Class A") {
				return new ClassARow(85);
			} else if (rowType == "Class B") {
				return new ClassBRow(85);
			} else {
				return new ClassCRow(85);
			}
		case 5:
			if (rowType == "Class A") {
				return new ClassARow(90);
			} else if (rowType == "Class B") {
				return new ClassBRow(90);
			} else if (rowType == "Class C") {
				return new ClassCRow(90);
			} else {
				return new ClassDRow(90);
			}
		case 6:
			if (rowType == "Basic") {
				return new BasicRow(95);
			} else if (rowType == "Class A") {
				return new ClassARow(95);
			} else if (rowType == "Class B") {
				return new ClassBRow(95);
			} else if (rowType == "Class C") {
				return new ClassCRow(95);
			} else if (rowType == "Class D") {
				return new ClassDRow(95);
			} else {
				return new ClassERow(95);
			}
		default:
			return null;

		}
	}
}
