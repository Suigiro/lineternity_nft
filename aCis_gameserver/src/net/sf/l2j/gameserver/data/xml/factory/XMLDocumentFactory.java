package net.sf.l2j.gameserver.data.xml.factory;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public final class XMLDocumentFactory
{
	private final DocumentBuilder _builder;
	
	private final Transformer _transformer;
	
	public static final XMLDocumentFactory getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected XMLDocumentFactory() throws Exception
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			this._builder = factory.newDocumentBuilder();
			this._transformer = TransformerFactory.newInstance().newTransformer();
		}
		catch (Exception e)
		{
			throw new Exception("Failed initializing", e);
		}
	}
	
	public final Document loadDocument(String filePath) throws Exception
	{
		return loadDocument(new File(filePath));
	}
	
	public final Document loadDocument(File file) throws Exception
	{
		if (!checkFile(file))
			throw new Exception("File: " + file.getAbsolutePath() + " doesn't exist and/or is not a file.");
		return this._builder.parse(file);
	}
	
	@SuppressWarnings("resource")
	public final void writeDocument(String filePath, Document doc) throws Exception
	{
		File file = new File(filePath);
		this._transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(file)));
	}
	
	public final Document newDocument()
	{
		return this._builder.newDocument();
	}
	
	private static final boolean checkFile(File file)
	{
		if (!file.exists())
			return false;
		if (!file.isFile())
			return false;
		return true;
	}
	
	private static class SingletonHolder
	{
		protected static final XMLDocumentFactory _instance;
		
		static
		{
			try
			{
				_instance = new XMLDocumentFactory();
			}
			catch (Exception e)
			{
				throw new ExceptionInInitializerError(e);
			}
		}
	}
}
