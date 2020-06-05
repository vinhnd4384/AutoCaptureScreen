# Introduction 
TODO: ScreenShot a list websites (full screen + responsive design). 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Download and setup newest JDK : https://www.oracle.com/java/technologies/javase-jdk13-downloads.html
2.	config in [Website_List.xlsx]
	2.1. Sheet [Device]:
		+ [B column - Device Type] device type like iphone, tablet. Note: don't use SPACE, do not duplicate name
		+ [C column - Capture] : TRUE : will capture , FALSE : do not capture with device type
		+ [F column - folder path] : Asoluted path which store screenshot image. Note : do not use "\" => must input "\\". and locate in [D://Images]
	2.2. Sheet [URL]:
		+ [D column - URL] URL of websites. Do not change any value in [C column] which is fill automatically via formular
		+ [C column - Need Capture] : TRUE : will capture website , FALSE : do not capture website with device type
		+ [F and G column - folder path] : Username / password to login website if this website use prompt alert when initilize site
		+ [H column - Have Popup] : don't use currently
3.	Copy [AutoCaptureTool] to local and locate anywhere you want. But prefer  in [C:\\AutoCaptureTool]
		+ Path: https://eastgatesoftware.sharepoint.com/sites/ProjectReinstil-Websites/Shared%20Documents/Forms/AllItems.aspx?viewid=db755e55%2Df554%2D40a5%2D9456%2D31dece3b4136&id=%2Fsites%2FProjectReinstil%2DWebsites%2FShared%20Documents%2FGeneral%2FTesting%2FAutoCaptureTool
4. 	How to run:
		+ Click [run_AutoCaptureTool.bat]
		+ When batch run completedly (show "Completed!!" in cmd screen), you can view result. You could view log file in folder [AutoCaptureTool/log]