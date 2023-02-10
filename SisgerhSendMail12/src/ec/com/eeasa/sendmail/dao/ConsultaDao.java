/*
* KempresaFactory.java 27/11/14
* Copyright 2014 INEC-DIZ3C, Inc. All rights reserved.
*/

package ec.com.eeasa.sendmail.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import ec.com.eeasa.sendmail.util.ConnectionManager;
/**
* Clase KempresaFactory. Contiene los metodos necesarios para el manejo de la informacion de la tabla public.KEMPRESA.
* Creado el 27/11/14
* @author: INEC-DIZ3C
* @version: 1.0
*/
public class ConsultaDao{	
	
	public ConsultaDao(){}
	
	/**
	* Obtiene la informacion de ZONAS.
	* @return ArrayList<HashMap<String, Object>> Registros seleccionados.
	*/
	public ArrayList<HashMap<String, String>> selectAllZona()throws Exception{
		String sql_fun = "SELECT DMZON_CODIGO,DMZON_NOMBRE " +
						 "FROM ROOTSISGERH.DRI_MA_ZONA " +
						 "WHERE DMZON_ESTADO = 1";
		ArrayList<HashMap<String, String>> listZona= new ArrayList<HashMap<String,String>>();		
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql_fun);			
			ResultSet resultados=ps.executeQuery();								
			while(resultados.next()){				
				HashMap<String, String> regimenLab = new HashMap<String, String>();
				regimenLab.put("DMZON_CODIGO",resultados.getString(1));
				regimenLab.put("DMZON_NOMBRE",resultados.getString(2));			
				listZona.add(regimenLab);
			}			
			ConnectionManager.closeConecction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionManager.closeConecction();
			e.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listZona;
	}
	
	
	/**
	* Obtiene la informacion de TIPOS DE NOMINA.
	* @return ArrayList<HashMap<String, Object>> Registros seleccionados.
	*/
	public ArrayList<HashMap<String, String>> selectAllTipoNomina(String inCodigoRegimen)throws Exception{
		String sql_fun = "SELECT DNMTR_CODIGO,DNMTR_NOMBRE,DNMTR_ESTADO_PERSONA " +
						 "FROM ROOTSISGERH.DRI_NOM_TIPO_ROL " +
						 "WHERE DRLAB_CODIGO = ? AND DNMTR_ESTADO = 1";
		ArrayList<HashMap<String, String>> listTipoNomina= new ArrayList<HashMap<String,String>>();		
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql_fun);	
			ps.setString(1, inCodigoRegimen);
			ResultSet resultados=ps.executeQuery();								
			while(resultados.next()){				
				HashMap<String, String> tipoNomina = new HashMap<String, String>();
				tipoNomina.put("DNMTR_CODIGO",resultados.getString(1));
				tipoNomina.put("DNMTR_NOMBRE",resultados.getString(2));			
				tipoNomina.put("DNMTR_ESTADO_PERSONA",resultados.getString(3));
				listTipoNomina.add(tipoNomina);
			}			
			ConnectionManager.closeConecction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionManager.closeConecction();
			e.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listTipoNomina;
	}
	
	/**
	* Obtiene la informacion de NOMINAS.
	* @return ArrayList<HashMap<String, Object>> Registros seleccionados.
	*/
	public ArrayList<HashMap<String, String>> selectAllNomina(String inTipoNomina, String inAnio, String inMes)throws Exception{
		String sql_fun = "SELECT DNMPN_CODIGO,DNMPN_NOMBRE_NOMINA " +
						 "FROM ROOTSISGERH.DRI_NOM_PAGO_NOMINA " +
						 "WHERE DNMTR_CODIGO = ? " +
						 "AND DNMPN_ANIO = ? " +
						 "AND DNMPN_MES = ? " +
						 //"AND DNMPN_ESTADO_NOMINA = 2 " +
						 "AND DNMPN_ESTADO = 1";
		ArrayList<HashMap<String, String>> listNomina= new ArrayList<HashMap<String,String>>();		
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql_fun);	
			ps.setString(1, inTipoNomina);
			ps.setString(2, inAnio);
			ps.setString(3, inMes);
			ResultSet resultados=ps.executeQuery();								
			while(resultados.next()){				
				HashMap<String, String> tipoNomina = new HashMap<String, String>();
				tipoNomina.put("DNMPN_CODIGO",resultados.getString(1));
				tipoNomina.put("DNMPN_NOMBRE_NOMINA",resultados.getString(2));			
				listNomina.add(tipoNomina);
			}			
			ConnectionManager.closeConecction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionManager.closeConecction();
			e.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listNomina;
	}
	
	/**
	* Obtiene la informacion de REGIMEN LABORAL.
	* @return ArrayList<HashMap<String, Object>> Registros seleccionados.
	*/
	public ArrayList<HashMap<String, String>> selectAllRegimenLab()throws Exception{
		String sql_fun = "SELECT DRLAB_CODIGO,DRLAB_DESCRIPCION " +
						 "FROM ROOTSISGERH.DRI_REGIMEN_LABORAL " +
						 "WHERE DRLAB_ESTADO = 1";
		ArrayList<HashMap<String, String>> listRegimenLab= new ArrayList<HashMap<String,String>>();		
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql_fun);			
			ResultSet resultados=ps.executeQuery();								
			while(resultados.next()){				
				HashMap<String, String> regimenLab = new HashMap<String, String>();
				regimenLab.put("DRLAB_CODIGO",resultados.getString(1));
				regimenLab.put("DRLAB_DESCRIPCION",resultados.getString(2));			
				listRegimenLab.add(regimenLab);
			}			
			ConnectionManager.closeConecction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionManager.closeConecction();
			e.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listRegimenLab;
	}		
	
	/**
	 * Obtiene un vector dinamico que contiene informacion del personal en base a un select dinamico
	 * @param inConnection
	 * @param inCondicion condicion que se aplicara al query
	 * @return vector dinamico.
	 * @throws SQLException
	 */
	public void selectByTipoNomina(
			int inDNMTR_ESTADO_PERSONA,
			String inDMZON_CODIGO,
			String inDNMPN_CODIGO,
			JTable inPersonalTable
	){
		CallableStatement tmpStmSelect = null;
		ResultSet tmpResults = null;
		try {
			Connection inConnection=ConnectionManager.getConnection();
			tmpStmSelect = inConnection.prepareCall("{ call ROOTSISGERH.SP_S_TIPO_NOMINA(?,?,?,?) }");
			tmpStmSelect.registerOutParameter(1, OracleTypes.CURSOR);
			tmpStmSelect.setInt(2, inDNMTR_ESTADO_PERSONA);
			tmpStmSelect.setString(3, inDMZON_CODIGO);
			tmpStmSelect.setString(4,inDNMPN_CODIGO);
			tmpStmSelect.execute();
			tmpResults = ((OracleCallableStatement) tmpStmSelect).getCursor(1);
			inPersonalTable.setModel(DbUtils.resultSetToTableModel(tmpResults));
			tmpResults.close();
			ConnectionManager.closeConecction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionManager.closeConecction();
			e.printStackTrace();
		}
		finally{
			try {
				
				tmpStmSelect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}