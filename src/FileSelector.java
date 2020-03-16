import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/* REF This code is taken from mkyong.com : /https://www.mkyong.com/swing/java-swing-jfilechooser-example/ **************/

public class FileSelector {

	public static File[] main() 
	{
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("File Selection");
		jfc.setMultiSelectionEnabled(true);
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);;
		File files[] = null;
		
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			files = jfc.getSelectedFiles();
			
			//System.out.println("Directories Found");
			Arrays.asList(files).forEach(x -> {
				if (x.isDirectory())
				{
					//System.out.println(x.getName());
				}
			});
			//System.out.println("\n---------------------\n");
			//System.out.println("Files Found\n");
			Arrays.asList(files).forEach(x -> {
				if(x.isFile()) {
					//System.out.print(x.getName());
				}
			});
			

		}
		
		return files;
	}

	// End of REF This code is taken from mykong.com /https://www.mkyong.com/swing/java-swing-jfilechooser-example/
}