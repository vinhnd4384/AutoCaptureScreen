@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @screenshot
  Scenario: Capture many sites which are define in excel file.
    Given Close popup
    #Given screenshot "D:\\Images" 
    Given Capture on responsive with length "812",width "375" and device type "Iphone" with file path "D:\\Images\\"
    #Given Capture on responsive with length "1440",width "900" and device type "Laptop" with file path "D:\\Images\\"
