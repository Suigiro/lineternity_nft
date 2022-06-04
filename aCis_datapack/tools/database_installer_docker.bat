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

echo "Deleting all tables for new content."
$MYG < ../sql/gs_install.sql &> /dev/null
$MYG < ../sql/full_install.sql &> /dev/null
$MYL < ../sql/full_install.sql &> /dev/null

$MYL < ../sql/sql/accounts.sql &> /dev/null
$MYL < ../sql/sql/gameservers.sql &> /dev/null

$MYG < ../sql/sql/auction_bid.sql &> /dev/null
$MYG < ../sql/sql/auction_table.sql &> /dev/null
$MYG < ../sql/sql/augmentations.sql &> /dev/null
$MYG < ../sql/sql/bookmarks.sql &> /dev/null
$MYG < ../sql/sql/buffer_schemes.sql &> /dev/null
$MYG < ../sql/sql/buylists.sql &> /dev/null
$MYG < ../sql/sql/castle.sql &> /dev/null
$MYG < ../sql/sql/castle_doorupgrade.sql &> /dev/null
$MYG < ../sql/sql/castle_manor_procure.sql &> /dev/null
$MYG < ../sql/sql/castle_manor_production.sql &> /dev/null
$MYG < ../sql/sql/castle_trapupgrade.sql &> /dev/null
$MYG < ../sql/sql/character_friends.sql &> /dev/null
$MYG < ../sql/sql/character_hennas.sql &> /dev/null
$MYG < ../sql/sql/character_macroses.sql &> /dev/null
$MYG < ../sql/sql/character_mail.sql &> /dev/null
$MYG < ../sql/sql/character_memo.sql &> /dev/null
$MYG < ../sql/sql/character_memo_dungeon.sql &> /dev/null
$MYG < ../sql/sql/character_quests.sql &> /dev/null
$MYG < ../sql/sql/character_raid_points.sql &> /dev/null
$MYG < ../sql/sql/character_recipebook.sql &> /dev/null
$MYG < ../sql/sql/character_recommends.sql &> /dev/null
$MYG < ../sql/sql/character_shortcuts.sql &> /dev/null
$MYG < ../sql/sql/character_skills.sql &> /dev/null
$MYG < ../sql/sql/character_skills_save.sql &> /dev/null
$MYG < ../sql/sql/character_subclasses.sql &> /dev/null
$MYG < ../sql/sql/characters.sql &> /dev/null
$MYG < ../sql/sql/clan_data.sql &> /dev/null
$MYG < ../sql/sql/clan_privs.sql &> /dev/null
$MYG < ../sql/sql/clan_skills.sql &> /dev/null
$MYG < ../sql/sql/clan_subpledges.sql &> /dev/null
$MYG < ../sql/sql/clan_wars.sql &> /dev/null
$MYG < ../sql/sql/clanhall.sql &> /dev/null
$MYG < ../sql/sql/clanhall_functions.sql &> /dev/null
$MYG < ../sql/sql/cursed_weapons.sql &> /dev/null
$MYG < ../sql/sql/dungeon.sql &> /dev/null
$MYG < ../sql/sql/fishing_championship.sql &> /dev/null
$MYG < ../sql/sql/forums.sql &> /dev/null
$MYG < ../sql/sql/games.sql &> /dev/null
$MYG < ../sql/sql/grandboss_data.sql &> /dev/null
$MYG < ../sql/sql/grandboss_list.sql &> /dev/null
$MYG < ../sql/sql/heroes_diary.sql &> /dev/null
$MYG < ../sql/sql/heroes.sql &> /dev/null
$MYG < ../sql/sql/items.sql &> /dev/null
$MYG < ../sql/sql/items_on_ground.sql &> /dev/null
$MYG < ../sql/sql/icons.sql &> /dev/null
$MYG < ../sql/sql/mdt_bets.sql &> /dev/null
$MYG < ../sql/sql/mdt_history.sql &> /dev/null
$MYG < ../sql/sql/mods_wedding.sql &> /dev/null
$MYG < ../sql/sql/olympiad_data.sql&> /dev/null
$MYG < ../sql/sql/olympiad_fights.sql&> /dev/null
$MYG < ../sql/sql/olympiad_nobles_eom.sql&> /dev/null
$MYG < ../sql/sql/olympiad_nobles.sql&> /dev/null
$MYG < ../sql/sql/pets.sql &> /dev/null
$MYG < ../sql/sql/posts.sql &> /dev/null
$MYG < ../sql/sql/raidboss_spawnlist.sql &> /dev/null
$MYG < ../sql/sql/random_spawn.sql &> /dev/null
$MYG < ../sql/sql/random_spawn_loc.sql &> /dev/null
$MYG < ../sql/sql/server_memo.sql &> /dev/null
$MYG < ../sql/sql/seven_signs.sql &> /dev/null
$MYG < ../sql/sql/seven_signs_festival.sql &> /dev/null
$MYG < ../sql/sql/seven_signs_status.sql &> /dev/null
$MYG < ../sql/sql/siege_clans.sql &> /dev/null
$MYG < ../sql/sql/spawnlist_4s.sql &> /dev/null
$MYG < ../sql/sql/spawnlist.sql &> /dev/null
$MYG < ../sql/sql/topic.sql &> /dev/null
echo ""
echo "Was fast, isn't it ?"