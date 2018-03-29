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
			return new BasicRow();
		case 2:
			if (rowType == "basic") {
				return new BasicRow();
			} else {
				return new ClassARow();
			}
		case 3:
			if (rowType == "basic") {
				return new BasicRow();
			} else if (rowType == "a") {
				return new ClassARow();
			} else {
				return new ClassBRow();
			}
		case 4:
			if (rowType == "a") {
				return new ClassARow();
			} else if (rowType == "b") {
				return new ClassBRow();
			} else {
				return new ClassCRow();
			}
		case 5:
			if (rowType == "a") {
				return new ClassARow();
			} else if (rowType == "b") {
				return new ClassBRow();
			} else {
				return new ClassDRow();
			}
		case 6:
			if (rowType == "Basic") {
				return new BasicRow();
			} else if (rowType == "a") {
				return new ClassARow();
			} else if (rowType == "b") {
				return new ClassARow();
			} else if (rowType == "c") {
				return new ClassARow();
			} else if (rowType == "d") {
				return new ClassARow();
			} else {
				return new ClassDRow();
			}
		default:
			return null;

		}
	}
}
