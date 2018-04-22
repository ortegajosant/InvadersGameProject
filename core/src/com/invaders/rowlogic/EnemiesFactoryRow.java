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
				return new BasicRow(85);
			} else {
				return new ClassARow(85);
			}
		case 2:
			if (rowType == "Basic") {
				return new BasicRow(90);
			} else if (rowType == "Class A") {
				return new ClassARow(90);
			} else {
				return new ClassBRow(90);
			}
		case 3:
			if (rowType == "Class A") {
				return new ClassARow(95);
			} else if (rowType == "Class B") {
				return new ClassBRow(95);
			} else if(rowType == "Class C") {
				return new ClassCRow(95);
			} else {
				return new ClassDRow(95);
			}
		case 4:
			if (rowType == "Class B") {
				return new ClassBRow(100);
			} else if (rowType == "Class C") {
				return new ClassCRow(100);
			} else if (rowType == "Class D") {
				return new ClassDRow(100);
			} else {
				return new ClassERow(100);
			}
		default:
			return null;

		}
	}
}
