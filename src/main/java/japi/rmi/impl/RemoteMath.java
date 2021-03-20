/**
 * 
 */
package japi.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import japi.rmi.interfaces.IRemoteMath;

/**
 * @author zacattle
 *
 */
public class RemoteMath extends UnicastRemoteObject implements IRemoteMath {

	private int numberOfComputations;
	
	public RemoteMath() throws RemoteException {
		numberOfComputations = 0;
	}
	
	@Override
	public double add(double a, double b) throws RemoteException {
		numberOfComputations++;
		System.out.println("Number of computations performed so far = " 
				+ numberOfComputations);
		return (a+b);
	}

	@Override
	public double subtract(double a, double b) throws RemoteException {
		numberOfComputations++;
		System.out.println("Number of computations performed so far = " 
				+ numberOfComputations);
		return (a-b);
	}

}
