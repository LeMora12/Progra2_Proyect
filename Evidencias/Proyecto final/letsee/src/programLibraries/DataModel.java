package programLibraries;

import javax.swing.table.DefaultTableModel;

import java.io.Serializable;

/*
 *Almacena los modelos de datos para clientes y argumentos.
 * 
 *@author clcardenas@unah.hn
 *@version 0.0.1
 *@date 1/05/24
 *@since 1/05/24 	
 */

public class DataModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel client;
	private DefaultTableModel argument;
	
	/*
	 *Se inicializan los modelos de datos para clientes y argumentos.
	 * 
	 *@author clcardenas@unah.hn
	 *@version 0.0.1
	 *@date 1/05/24
	 *@since 1/05/24 	
	 */
	
	@SuppressWarnings("serial")
	public DataModel() {
		this.argument = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha", "Contenido"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		this.client = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Numero Tel\u00E9fonico", "Correo Electronico", "Observaci\u00F3n"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
	}
	
	/*
	 *Obtiene el modelo de datos para cliente.
	 * 
	 *@author clcardenas@unah.hn
	 *@version 0.0.1
	 *@date 1/05/24
	 *@since 1/05/24 
	 *@return el modelo de datos para cliente
	 */
	
	public DefaultTableModel getClient() {
		return client;
	}

	/*
	 *Establece el modelo de datos para cliente.
	 * 
	 *@author clcardenas@unah.hn
	 *@version 0.0.1
	 *@date 1/05/24
	 *@since 1/05/24
	 *@param client es el modelo de datos para cliente a establecer.  	
	 */
	
	public void setClient(DefaultTableModel client) {
		this.client = client;
	}

	/*
	 *Obtiene el modelo de datos para argumento.
	 * 
	 *@author clcardenas@unah.hn
	 *@version 0.0.1
	 *@date 1/05/24
	 *@since 1/05/24 
	 *@return el modelo de datos para argumento
	 */
	
	public DefaultTableModel getArgument() {
		return argument;
	}

	/*
	 *Establece el modelo de datos para argumento.
	 * 
	 *@author clcardenas@unah.hn
	 *@version 0.0.1
	 *@date 1/05/24
	 *@since 1/05/24
	 *@param argument es el modelo de datos para argumento a establecer.  	
	 */
	
	public void setArgument(DefaultTableModel argument) {
		this.argument = argument;
	}
	
}
