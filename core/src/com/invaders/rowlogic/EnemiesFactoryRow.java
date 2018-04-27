package com.invaders.rowlogic;

/**
 * Factory de hileras de enemigos
 * 
 * @author jorte
 *
 */
public class EnemiesFactoryRow {

	private static EnemiesFactoryRow instance;

	private EnemiesFactoryRow() {

	}

	/**
	 * Crea una instancia de la Factory, esta es Singleton
	 * @return EnemiesFactoryRow
	 */
	public static EnemiesFactoryRow getInstance() {
		if (instance == null) {
			instance = new EnemiesFactoryRow();
		}
		return instance;
	}

	/**
	 * Crea una nueva hilera de enemigos
	 * 
	 * @param level
	 *            int / Nivel en el que se encuentra
	 * @param rowType
	 *            / Tipo de hilera a crear
	 * @return AbstractEnemyRow / Hilera de enemigos
	 */
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
				return new BasicRow(95);
			} else if (rowType == "Class A") {
				return new ClassARow(95);
			} else {
				return new ClassBRow(95);
			}
		case 3:
			if (rowType == "Class B") {
				return new ClassARow(105);
			} else if (rowType == "Class C") {
				return new ClassCRow(105);
			} else {
				return new ClassDRow(105);
			}
		case 4:
			if (rowType == "Class D") {
				return new ClassDRow(115);
			} else {
				return new ClassERow(100);
			}
		default:
			return null;

		}
	}
}
