package com.invaders.rowlogic;

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
			if (rowType == "Basic") {
				return new BasicRow(75);
			} else {
				return new ClassARow(75);
			}
		case 2:
			if (rowType == "Basic") {
				return new BasicRow(80);
			} else if (rowType == "Class A") {
				return new ClassARow(80);
			} else {
				return new ClassBRow(80);
			}
		case 3:
			if (rowType == "Class A") {
				return new ClassARow(85);
			} else if (rowType == "Class B") {
				return new ClassBRow(85);
			} else if(rowType == "Class C") {
				return new ClassCRow(85);
			} else if (rowType == "Basic") {
				return new BasicRow(85);
			} else {
				return new ClassDRow(85);
			}
		case 4:
			if (rowType == "Class B") {
				return new ClassBRow(90);
			} else if (rowType == "Class C") {
				return new ClassCRow(90);
			} else if (rowType == "Class D") {
				return new ClassDRow(90);
			} else {
				return new ClassERow(90);
			}
		default:
			return null;

		}
	}
}
