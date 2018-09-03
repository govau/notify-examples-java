#!/usr/bin/env bash

app_name=notify-examples-java

deployed_apps=$(./bin/cf apps \
        | grep ${app_name} \
        | sed -e 's/ .*//g'  -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//' \
        | sort \
        | tail -n +2 )

active_branches=`git remote prune origin && git branch -r \
        | grep -v origin\/master \
        | sed -e "s/origin\//${app_name}-/g" -e "s/^[[:space:]]*//" -e "s/[[:space:]]*$//" \
        | sort`

inactive_apps=$(comm -23 <(echo "${deployed_apps}") <(echo "${active_branches}"))

echo "---------------------------------------------------"
echo "Active applications in Cloud Foundry:"
echo "${deployed_apps}"
echo "---------------------------------------------------"
echo -e "Active git branches:"
echo "${active_branches}"
echo "---------------------------------------------------"
echo -e "Inactive Cloud Foundry applications:"
echo "${inactive_apps}"


echo -e "\n==================================================="
if [[ ! -z ${inactive_apps} ]]
then
    while read unused_app; do
        echo "Deleting unused app: ${unused_app}..."
        echo y | ./bin/cf delete ${unused_app}
        echo "Deleted unused app: ${unused_app}"
    done <<< "${inactive_apps}"
else
    echo "All ${app_name} apps are active"
fi

echo -e "==================================================="