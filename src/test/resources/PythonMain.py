import json
import os
import glob
import pprint
import datetime
import time
import sys
path = sys.argv[-1]

def convert_time(seconds):
   hours  = seconds/3600
   seconds = seconds%3600
   minutes = seconds/60
   seconds = seconds%60
   return hours,minutes, seconds

all_files = os.listdir(path)
parent_dict = {}
child_dict = {}
for file in all_files:
    file = path+'/'+file
    with open(file,'r') as f:
        j = json.load(f)
        if file.endswith('-container.json'):
            if j['name'] != 'runner.TestRunner':
                parent_dict[(j['name'],j['start'],j['stop'])] = j['children']
                
            else:
                
        else:
            child_dict[j['uuid']] = j
            
for key,val in parent_dict.items():
    for i,uuid in enumerate(val):
        val[i] = child_dict[uuid]


header = r"""<html width="100%">
<head>
<title></title>
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
		.collapsible { background-color: #e6e6ff;  color: #800040;  cursor: pointer;  padding: 10px 10px 10px 30px;  width: 98.5%;  border: none;  text-align: left;  outline: none;  font-size: 17px; margin:10px 0px 0px 7px;}
		.active, .collapsible:hover { background-color: #ccccff;}
		.collapsible:after {  content: '\002B';  color: white;  font-weight: bold;  float: right;  margin-left: 5px;}
		.active:after {  content: "\2212";}
		.content {  padding: 0 18px;  max-height: 0;  overflow: hidden;
		transition: max-height 0.2s ease-out;}	
		body { color:#800040;font-family:Helvetica,Arial,sans-serif; width:auto; border: 1px solid black;}
		div.topheaderleft {width: 100%;text-align:left;padding-left:15px; font-size:14px;padding-bottom:10px; color:#800040;}
		div.topheaderright {padding: 10px 10px 10px -10px;  width: 97.5%; text-align:right;display: inline-block; margin-left: 15px;}
		div.passcount{width:15px; border: 1px solid black; margin-left:30px;}
		table, th { margin-left:10px; }
		td{ width:auto; padding:5px; margin:20px; color:#ffffff;}
		div.fail{border: 2px solid #fd5a3e; border-radius:6px; background-color:#fd5a3e; padding-left:10px;padding-right:10px;}
		div.pass{border: 2px solid #97cc64; border-radius:6px; background-color:#97cc64; padding-left:10px;padding-right:10px;}
		div.skipped{border: 2px solid #aaa; border-radius:6px; background-color:#aaa; padding-left:10px;padding-right:10px;}
		div.broken{border: 2px solid #ffd050; border-radius:6px; background-color:#ffd050; padding-left:10px;padding-right:10px;}	
		div.unknown{border: 2px solid #d35ebe; border-radius:6px; background-color:#d35ebe; padding-left:10px;padding-right:10px;}
       div.parentSuite{	width: 80%; text-align:left;display: inline-block; color:#800040;padding-left:15px;margin-right:900px;}	
	   div.line{width:98%;border-bottom:2px solid #e0e0d1; margin-left:1%;}
	   div.failSubsuite{border: 2px solid #fd5a3e; border-radius:6px; background-color:#fd5a3e; padding-left:10px;padding-right:10px; font-weight:normal; font-size:12px;}
		div.passSubsuite{border: 2px solid #97cc64; border-radius:6px; background-color:#97cc64; padding-left:10px;padding-right:10px; font-weight:normal; font-size:12px;}
		div.skippedSubsuite{border: 2px solid #aaa; border-radius:6px; background-color:#aaa; padding-left:10px;padding-right:10px; font-weight:normal; font-size:12px;}
		div.brokenSubsuite{border: 2px solid #ffd050; border-radius:6px; background-color:#ffd050; padding-left:10px;padding-right:10px; font-weight:normal; font-size:12px;}	
		div.unknownSubsuite{border: 2px solid #d35ebe; border-radius:6px; background-color:#d35ebe; padding-left:10px;padding-right:10px; font-weight:normal; font-size:12px;}
		div.parameter{color:#800040; padding:0px;margin:0px;font-weight:normal; font-size:12px;}
		div.steps{color:#800040; padding:0px;margin:0px;font-weight:normal; font-size:14px;}
		div.stepsPass{padding:0px;margin:0px;font-weight:normal; font-size:14px; color:#97cc64}
		div.stepsFail{padding:0px;margin:0px;font-weight:normal; font-size:14px; color:#fd5a3e;}
        .trace_div{ border-radius:6px; background-color:#e6e6e6;padding:10px 10px 10px 10px;}
        .tcpass{color:#97cc64;}
		.tcfail{color:#fd5a3e;}
		.tcskipped{color:#aaa;}
		.tcbroken{color:#ffd050;}
        .trace_td{color:#fd5a3e; padding-left:10px;padding-right:10px;align:right; width: 400px; font-size:12px;}
        div.step_data{padding-left:10px;padding-right:10px;font-size:11px;color:#800040}
        div.parent_execution_timedispaly{color:#800040;padding-left:15px;padding-right:10px;align:right; width: 400px; font-size:14px;}
        div.PieChartDiv{width:100%;}
	   
    </style>
</head>
<div data-role="page" id="pageone">
<body>
<div class="parentSuite">
<h3>{parentSuite_Name}</h3></div>
<div class="line"></div>
<div class="PieChartDiv";>
<div id="piechart"></div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Task', 'Test Execution Result'],
  ['Skipped', {total_skipped}],
  ['Fail', {total_fail}],
  ['Broken', {total_broken}],
  ['Pass', {total_pass}],
  ['Unknown', {total_unknown}]
]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Test execution result: ', 'width':1550, 'height':300};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>
<div class="line"></div>
</div>
</br>




<div class="topheaderleft"><span text-align="center"><b>Execution result date :</b> {execution_date} <span> ,<b> Start Time: </b>{start}, <b>End Time: </b>{stop},<b> Total Duration:</b> {execution_time}</span></span></div>
<div class="topheaderright">
	<table> 
	   <tr> 
		  <td><div class="pass">Pass: {total_pass}</div></td> 
		  <td><div class="fail">Fail: {total_fail}</div></td> 
		  <td><div class="skipped">Skipped: {total_skipped}</div></td> 
		  <td><div class="broken">broken: {total_broken}</div></td> 
		  <td><div class="unknown">unknown: {total_unknown}</div></td> 
	   </tr> 
	</table>
</div>
<div>

			

</div>
<div class="line"></div>




{body_end}



</div>
</body>





</html>

"""


parent_test_summary = """
 <div data-role="main" class="ui-content">
    {main_parent}
  </div>
	   
"""

main_parent = """
 <div data-role="main" class="ui-content">
    <div data-role="collapsible">
      <h3>{Parent_Name}</h3>
      <table> 
	   <tr> 
		  {parent_test_summary}
	   </tr>
	   </table>
	   
        {test_case}
      
    </div>
    </div>
	"""

parent_test = """
           <div class="parent_execution_timedispaly";> <h6 text-align="center">Suite Execution Duration: {parent_execution_time}</h6></div></tr><tr>
          <td><div class="passSubsuite">Pass: {parent_pass}</div></td> 
		  <td><div class="failSubsuite">Fail: {parent_fail}</div></td> 
		  <td><div class="skippedSubsuite">Skipped: {parent_skipped}</div></td> 
		  <td><div class="brokenSubsuite">broken: {parent_broken}</div></td> 
		  <td><div class="unknownSubsuite">unknown: {parent_unknown}</div></td>
"""
test_cases = """
      <div data-role="collapsible">
      <tr>
        <h5>{test_name}<span class="tcpass">    {PASSED} </span><span class="tcfail">    {FAILED} </span> 
                       <span class="tcskipped">    {SKIPPED} </span><span class="tcbroken">    {BROKEN} </span></h5>
        <table></br>
        <div  class="trace_div"> {test_trace}</div></br>
		<tr>
		<td><div  class="parameter">Duration:</div></td>
		<td><div  class="parameter">{test_duration}</div></td>
		</tr>
		<tr>
		<td><div  class="parameter">browserName:</div></td>
		<td><div  class="parameter">{test_browser}</div></td>
		</tr>
				<tr>
		<td><div  class="parameter">browserVersion:</div></td>
		<td><div  class="parameter">{browser_version}</div></td>
		</tr>
		<tr>
		<td><div  class="parameter">platform:</div></td>
		<td><div  class="parameter">{test_platform}</div></td>
		</tr>
		</table>
		<br/>
      </tr>
      	<table>
		{tc_steps}
		</table>
      </div>
"""
tc_steps= """
		<tr>
		<td><div  class="steps">{step_name}:</div></td>
		<td><div  class="{step_result_class}">{step_result}</div></td>
		</tr>
        <tr>
		<td></td>
		<td>
            <table>
                <tr>
                    <td><div   class="step_data">{step_data_name}</div></td>
                    <td><div   class="step_data">{step_data_value}</div> </td>
                </tr>
            </table>
        
        </td>
		</tr>        
        <tr>
        <td></td>
        <td  class="trace_td">{test_failed_trace}</td>        
        </tr>
"""

        
        
total_pass = 0
total_fail = 0
total_skipped = 0
total_broken = 0
total_unknown = 0



main_parent_str = ""
parentSuite_Name=""
min_start =10000000000000000000000000000000000000000000000
max_end =0

for key, val in parent_dict.items():
    test_summary_str = ""
    test_details = ""

    parent_pass = 0
    parent_fail = 0
    parent_skipped = 0
    parent_broken = 0
    parent_unknown = 0
    name,start,end = key
#    execution_date =  time.ctime(start)#datetime.fromtimestamp(start).strftime('%Y-%m-%d')
#    start_time =  time.ctime(start)#datetime.fromtimestamp(start).strftime('%H:%M:%S')
#    end_time =  time.ctime(end)#datetime.fromtimestamp(end).strftime('%H:%M:%S')
    min_start = min(min_start,start)
    max_end = max(end,max_end)
   # print(s%(execution_date,start_time,end_time))
    for child in val:
        test_cases_str = test_cases
        tcpassed= ""
        tcfail= ""
        tcskipped= ""
        tcbroken= ""
        tcunknown= ""
        test_trace =""
        
        tc_steps_str= ""
        test_status = child['status']
        if test_status == 'passed':
            total_pass += 1
            parent_pass += 1
            tcpassed = "(PASSED)"
        elif test_status == 'failed':
            total_fail += 1
            parent_fail += 1
            tcfail = "(FAILED)"
            
        elif test_status == 'skipped':
            total_skipped += 1
            parent_skipped += 1
            tcskipped = "(SKIPPED)"
        elif test_status == 'broken':
            total_broken += 1
            parent_broken += 1
            tcbroken = "(BROKEN)"
            test_trace = child['statusDetails']['trace']
        elif test_status == 'unknown':
            total_unknown += 1
            parent_unknown += 1
            tcunknown = "(UNKNOWN)"
            test_trace = child['statusDetails']['trace']
        test_cases_str = test_cases_str.replace("{PASSED}",tcpassed).replace("{FAILED}",tcfail).replace("{SKIPPED}",tcskipped).replace("{BROKEN}",tcbroken).replace("{UNKNOWN}",tcunknown)
        test_name = child['name']
        for step in child['steps']:
            test_failed_trace = ""
            step_data_name = ""
            step_data_value =""
            if step['status'] =='passed':
                step_result_class = 'stepsPass'
            else:
                step_result_class = 'stepsFail' 
                test_failed_trace= step['statusDetails']['trace']
                for parameter in step['parameters']:
                    step_data_name =parameter['name']
                    step_data_value =parameter['value']
                     
          
                
            tc_steps_str += tc_steps.replace("{step_name}",step['name']).replace("{step_result}",step['status']).replace("{step_result_class}",step_result_class).replace("{test_failed_trace}",test_failed_trace).replace("{step_data_name}",step_data_name).replace("{step_data_value}",step_data_value)
        
        for param in child['parameters']:
            if param['name'] == 'browserName':
                test_browser = param['value']
            elif param['name'] == 'platform':
                test_platform = param['value']
            elif param['name'] == 'arg1':
                test_arg1 = param['value']
            elif param['name'] == 'browserVersion':
                browser_version = param['value']
            elif param['name'] == 'arg0':
                test_arg0 = param['value']

        for label in child['labels']:
            if label['name'] == 'parentSuite':
                parentSuite_Name = label['value']
        
        tc_pet = convert_time((child['stop']-child['start'])/1000)
        tc_execution_time = "%02d:%02d:%02d"%(tc_pet[0],tc_pet[1],tc_pet[2])
        test_cases_str= test_cases_str.replace("{test_duration}",tc_execution_time)
 
        test_details += test_cases_str.replace("{test_browser}",test_browser).replace("{test_platform}",test_platform).replace("{browser_version}",browser_version).replace("{test_name}",test_arg0 + ' '+ test_arg1).replace('"','').replace('{tc_steps}',tc_steps_str).replace('{test_trace}',test_trace)
    parent_test_str = parent_test.replace('{parent_pass}',str(parent_pass)).replace('{parent_fail}',str(parent_fail)).replace('{parent_skipped}',str(parent_skipped)).replace('{parent_broken}',str(parent_broken)).replace('{parent_unknown}',str(parent_unknown))
    main_parent_str += main_parent.replace('{Parent_Name}',name).replace('{test_case}',test_details).replace("{parent_test_summary}",parent_test_str)
    pet = convert_time((end-start)/1000)
    parent_execution_time = "%02d:%02d:%02d"%(pet[0],pet[1],pet[2])
    main_parent_str= main_parent_str.replace("{parent_execution_time}",parent_execution_time)


execution_duration = (max_end - min_start)/1000
max_end_time = time.gmtime(max_end/1000)
min_start_time = time.gmtime(min_start/1000)
execution_date= datetime.date(min_start_time.tm_year,min_start_time.tm_mon, min_start_time.tm_mday  ).strftime('%Y-%m-%d')

min_start_time= datetime.datetime(min_start_time.tm_year,min_start_time.tm_mon, min_start_time.tm_mday, min_start_time.tm_hour, min_start_time.tm_min, min_start_time.tm_sec).strftime('%H:%M:%S')
max_end_time= datetime.datetime(max_end_time.tm_year,max_end_time.tm_mon, max_end_time.tm_mday, max_end_time.tm_hour, max_end_time.tm_min, max_end_time.tm_sec).strftime('%H:%M:%S')


header = header.replace("{total_pass}",str(total_pass)).replace("{total_fail}",str(total_fail)).replace("{total_broken}",str(total_broken)).replace("{total_skipped}",str(total_skipped)).replace("{total_unknown}",str(total_unknown)).replace("{body_end}",main_parent_str).replace("{parentSuite_Name}", parentSuite_Name)

#print(header.encode('utf-8'))

hours,minutes,seconds = convert_time(execution_duration)

#print(execution_date.strftime('%Y-%m-%d'), min_start_time.strftime('%H:%M:%S'), max_end_time.strftime('%H:%M:%S'))
#print("hours :",hours,"minutes :", minutes,"seconds :", seconds)


header = header.replace("{execution_date}",execution_date).replace("{start}",min_start_time).replace("{stop}",max_end_time).replace("{execution_time}","%02d:%02d:%02d"%(hours,minutes,seconds))




f = open(path + '/' + 'report.html','w')
f.write(header.encode('utf-8'))
f.close()

print("report is available at location",os.path.join(path,'Execution_Report.html'))
