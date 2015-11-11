package system.adapter;

import imp.ImportProcessor;

public class ImportProcessorAdapter implements Runnable {
	private ImportProcessor ip;
	private boolean isRunning;

	public ImportProcessorAdapter(ImportProcessor ip) {
		this.ip = ip;
	}

	public void run() {
		this.isRunning = true;
		this.ip.run();
		this.isRunning = false;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public boolean start() {

		if (this.isRunning())
		{
			return false;
		}
		else
		{
			new Thread(this).start();
			return true;
		}
	}
}