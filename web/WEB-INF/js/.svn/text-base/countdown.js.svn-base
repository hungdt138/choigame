

            // NOTE: the month entered must be one less than current month. ie; 0=January, 11=December
            // NOTE: the hour is in 24 hour format. 0=12am, 15=3pm etc
            // format: dateFuture1 = new Date(year,month-1,day,hour,min,sec)
            // example: dateFuture1 = new Date(2003,03,26,14,15,00) = April 26, 2003 - 2:15:00 pm

            
            dateFuture1 = new Date(2012,0,4,15,43,19);
            function getparam(year, month, day, hour, minute, second){
               date1 = new Date(year, month, day, hour, minute, second);
               //alert(year);
            }

            // TESTING: comment out the line below to print out the "dateFuture" for testing purposes
            //document.write(dateFuture +"<br />");


            //###################################
            //nothing beyond this point
            function GetCount(year, month, day, hour, minute, second,iid){
                
                ddate = new Date(year, month, day, hour, minute, second);
                dateNow = new Date();	//grab current date
                amount = ddate.getTime() - dateNow.getTime();	//calc milliseconds between dates
                delete dateNow;

                // if time is already past
                if(amount < 0){
                    document.getElementById(iid).innerHTML="Now!";
                }
                // else date is still good
                else{
                    days=0;hours=0;mins=0;secs=0;out="";

                    amount = Math.floor(amount/1000);//kill the "milliseconds" so just secs

                    days=Math.floor(amount/86400);//days
                    amount=amount%86400;

                    hours=Math.floor(amount/3600);//hours
                    amount=amount%3600;

                    mins=Math.floor(amount/60);//minutes
                    amount=amount%60;

                    secs=Math.floor(amount);//seconds

                    if(days != 0){out += days +"  "+((days==1)?"day":"days")+": ";}
                    if(hours != 0){out += hours +"   "+((hours==1)?"hour":"")+":";}
                    out += mins +" "+((mins==1)?"min":"")+":";
                    out += secs +" "+((secs==1)?"sec":"")+":";
                    out = out.substr(0,out.length-2);
                    document.getElementById(iid).innerHTML="Thời gian còn lại: <font size = 3 ><b>"+ out + "</b></span>";

                    setTimeout(function(){GetCount(year, month, day, hour, minute, second,iid)}, 1000);
                }
            }

//            window.onload=function(){
//                GetCount(2012,0,4,15,43,19, 'countbox1');
//                //you can add additional countdowns here (just make sure you create dateFuture2 and countbox2 etc for each)
//            };