# Loginserver
LSDBHOST="mariadb"
LSDB="acis"
LSUSER="l2j"
LSPASS="lineternity"

# Gameserver
GSDBHOST="mariadb"
GSDB="acis"
GSUSER="l2j"
GSPASS="lineternity"

echo "                        aCis database installation"
echo "                        __________________________"
echo ""

MYSQLDUMPPATH=`which mysqldump 2>/dev/null`
MYSQLPATH=`which mysql 2>/dev/null`

MYL="mysql -h $LSDBHOST -u $LSUSER -p$LSPASS -D $LSDB"
MYG="mysql -h $GSDBHOST -u $GSUSER -p$GSPASS -D $GSDB"

# echo "Deleting all tables for new content."
# $MYG < ./gs_install.sql &> /dev/null
# $MYG < ./full_install.sql &> /dev/null
# $MYL < ./full_install.sql &> /dev/null

echo "Inserting data for all tables"
$MYL < ../sql/accounts.sql &> /dev/null
$MYL < ../sql/gameservers.sql &> /dev/null

$MYG < ../sql/auction_bid.sql &> /dev/null
$MYG < ../sql/auction_table.sql &> /dev/null
$MYG < ../sql/augmentations.sql &> /dev/null
$MYG < ../sql/bookmarks.sql &> /dev/null
$MYG < ../sql/buffer_schemes.sql &> /dev/null
$MYG < ../sql/buylists.sql &> /dev/null
$MYG < ../sql/castle.sql &> /dev/null
$MYG < ../sql/castle_doorupgrade.sql &> /dev/null
$MYG < ../sql/castle_manor_procure.sql &> /dev/null
$MYG < ../sql/castle_manor_production.sql &> /dev/null
$MYG < ../sql/castle_trapupgrade.sql &> /dev/null
$MYG < ../sql/character_friends.sql &> /dev/null
$MYG < ../sql/character_hennas.sql &> /dev/null
$MYG < ../sql/character_macroses.sql &> /dev/null
$MYG < ../sql/character_mail.sql &> /dev/null
$MYG < ../sql/character_memo.sql &> /dev/null
$MYG < ../sql/character_memo_dungeon.sql &> /dev/null
$MYG < ../sql/character_quests.sql &> /dev/null
$MYG < ../sql/character_raid_points.sql &> /dev/null
$MYG < ../sql/character_recipebook.sql &> /dev/null
$MYG < ../sql/character_recommends.sql &> /dev/null
$MYG < ../sql/character_shortcuts.sql &> /dev/null
$MYG < ../sql/character_skills.sql &> /dev/null
$MYG < ../sql/character_skills_save.sql &> /dev/null
$MYG < ../sql/character_subclasses.sql &> /dev/null
$MYG < ../sql/characters.sql &> /dev/null
$MYG < ../sql/clan_data.sql &> /dev/null
$MYG < ../sql/clan_privs.sql &> /dev/null
$MYG < ../sql/clan_skills.sql &> /dev/null
$MYG < ../sql/clan_subpledges.sql &> /dev/null
$MYG < ../sql/clan_wars.sql &> /dev/null
$MYG < ../sql/clanhall.sql &> /dev/null
$MYG < ../sql/clanhall_functions.sql &> /dev/null
$MYG < ../sql/cursed_weapons.sql &> /dev/null
$MYG < ../sql/dungeon.sql &> /dev/null
$MYG < ../sql/fishing_championship.sql &> /dev/null
$MYG < ../sql/forums.sql &> /dev/null
$MYG < ../sql/games.sql &> /dev/null
$MYG < ../sql/grandboss_data.sql &> /dev/null
$MYG < ../sql/grandboss_list.sql &> /dev/null
$MYG < ../sql/heroes_diary.sql &> /dev/null
$MYG < ../sql/heroes.sql &> /dev/null
$MYG < ../sql/items.sql &> /dev/null
$MYG < ../sql/items_on_ground.sql &> /dev/null
$MYG < ../sql/icons.sql &> /dev/null
$MYG < ../sql/mdt_bets.sql &> /dev/null
$MYG < ../sql/mdt_history.sql &> /dev/null
$MYG < ../sql/mods_wedding.sql &> /dev/null
$MYG < ../sql/olympiad_data.sql&> /dev/null
$MYG < ../sql/olympiad_fights.sql&> /dev/null
$MYG < ../sql/olympiad_nobles_eom.sql&> /dev/null
$MYG < ../sql/olympiad_nobles.sql&> /dev/null
$MYG < ../sql/pets.sql &> /dev/null
$MYG < ../sql/posts.sql &> /dev/null
$MYG < ../sql/raidboss_spawnlist.sql &> /dev/null
$MYG < ../sql/random_spawn.sql &> /dev/null
$MYG < ../sql/random_spawn_loc.sql &> /dev/null
$MYG < ../sql/server_memo.sql &> /dev/null
$MYG < ../sql/seven_signs.sql &> /dev/null
$MYG < ../sql/seven_signs_festival.sql &> /dev/null
$MYG < ../sql/seven_signs_status.sql &> /dev/null
$MYG < ../sql/siege_clans.sql &> /dev/null
$MYG < ../sql/spawnlist_4s.sql &> /dev/null
$MYG < ../sql/spawnlist.sql &> /dev/null
$MYG < ../sql/topic.sql &> /dev/null
echo ""
echo "Was fast, isn't it ?"