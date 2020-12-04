import csv
from xml.dom import minidom

# This function translates xml file, which formatted:
# <root>
#       <table_row>
#               <table_column>{value}</table_column>
#               <table_column>{value}</table_column>
#               <table_column>{value}</table_column>
#       </table_row>
# </root>
# to csv file convenient to download into database
def translate_from_xml_to_csv(source_path, destination_path, table_row_name):
        root = minidom.parse(source_path)
        table_rows = root.getElementsByTagName(table_row_name)
        column_names = column_values = []
        isFirstRow = True
        with open(destination_path, 'w', newline='') as csv_file:
                csv_writer = csv.writer(csv_file, delimiter=',')
                for table_row_index in range(0, table_rows.length):
                        columns = table_rows[table_row_index].childNodes
                        column_values = []
                        for column in columns:
                                if (column.nodeType == column.ELEMENT_NODE):
                                        column_names.append(column.nodeName)
                                        column_values.append(column.childNodes[0].nodeValue)
                        if (isFirstRow and column_names != []):
                                csv_writer.writerow(column_names)
                                isFirstRow = False
                        if (column_values != []):
                                csv_writer.writerow(column_values)
                
translate_from_xml_to_csv(r'C:\Users\User\Desktop\services.xml', r'C:\Users\User\Desktop\services.csv', 'service')
                        
                    


			
