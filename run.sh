echo "------------------------ CLOSING OLD SESSIONS -------------------------------"
screen -ls | grep Detached | grep hobusu | cut -d. -f1 | awk '{print $1}' | xargs kill

echo "------------------------ STARTING FRONTEND APPLICATION -----------------------------"
screen -d -m -S "hobusu-frontend-applcation" bash -c "cd ~/Projects/hobusu-front/; http-server;"

echo "------------------------ STARTING BACKEND APPLICATION -----------------------------"
screen -d -m -S "hobusu-backend-application" bash -c "cd ~/Projects/hobusu/; activator run;"

screen -ls;




