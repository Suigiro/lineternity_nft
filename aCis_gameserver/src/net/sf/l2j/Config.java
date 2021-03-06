package net.sf.l2j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import net.sf.l2j.commons.config.ExProperties;
import net.sf.l2j.commons.logging.CLogger;
import net.sf.l2j.commons.math.MathUtil;
import net.sf.l2j.gameserver.enums.OlympiadPeriod;
import net.sf.l2j.gameserver.model.holder.IntIntHolder;
import net.sf.l2j.gameserver.model.location.Location;

/**
 * This class contains global server configuration.<br>
 * It has static final fields initialized from configuration files.
 */
public final class Config {
	private static final CLogger LOGGER = new CLogger(Config.class.getName());

	public static final String CLANS_FILE = "./config/clans.properties";
	public static final String EVENTS_FILE = "./config/events.properties";
	public static final String GEOENGINE_FILE = "./config/geoengine.properties";
	public static final String HEXID_FILE = "./config/hexid.txt";
	public static final String LOGIN_CONFIGURATION_FILE = "./config/loginserver.properties";
	public static final String NPCS_FILE = "./config/npcs.properties";
	public static final String PLAYERS_FILE = "./config/players.properties";
	public static final String SERVER_FILE = "./config/server.properties";
	public static final String SIEGE_FILE = "./config/siege.properties";

	// LINETERNITY NFT
	// -------------------------------------------------
	// Dungeon Settings
	// -------------------------------------------------
	public static final String DUNGEON_REWARD_FILE = "./config/dev/dungeon.properties";

	public static int DUNGEON_COIN_ID;
	public static int CONT_DUNGEON_ITEM;
	public static int DUNGEON_SPAWN_X;
	public static int DUNGEON_SPAWN_Y;
	public static int DUNGEON_SPAWN_Z;
	public static int DUNGEON_SPAWN_RND;

	public static int DUNGEON_ITEM_RENEWAL0;
	public static int DUNGEON_ITEM_RENEWAL1;
	public static int DUNGEON_ITEM_RENEWAL2;
	public static int DUNGEON_ITEM_RENEWAL3;
	public static int DUNGEON_ITEM_RENEWAL4;
	public static int DUNGEON_ITEM_RENEWAL5;
	public static int DUNGEON_ITEM_RENEWAL6;
	public static int DUNGEON_ITEM_RENEWAL7;
	public static int DUNGEON_ITEM_RENEWAL8;
	public static int DUNGEON_ITEM_RENEWAL9;
	public static int DUNGEON_ITEM_RENEWAL10;

	public static int DUNGEON_PARTY_ITEM_RENEWAL0;
	public static int DUNGEON_PARTY_ITEM_RENEWAL1;
	public static int DUNGEON_PARTY_ITEM_RENEWAL2;
	public static int DUNGEON_PARTY_ITEM_RENEWAL3;
	public static int DUNGEON_PARTY_ITEM_RENEWAL4;

	// -------------------------------------------------
	// Events Settings
	// -------------------------------------------------

	public static final String LINETERNITY_EVENTS = "./config/dev/events.properties";

	/**
	 * Loads Dungeon settings.
	 */
	private static final void loadDungeon() {
		// Dungeon
		ExProperties SafeDungeon = initProperties(DUNGEON_REWARD_FILE);

		DUNGEON_COIN_ID = SafeDungeon.getProperty("DungeonCoinId", 57);
		CONT_DUNGEON_ITEM = SafeDungeon.getProperty("DungeonContItem", 1);
		DUNGEON_SPAWN_X = SafeDungeon.getProperty("DungeonSpawnX", 82635);
		DUNGEON_SPAWN_Y = SafeDungeon.getProperty("DungeonSpawnY", 148798);
		DUNGEON_SPAWN_Z = SafeDungeon.getProperty("DungeonSpawnZ", -3464);
		DUNGEON_SPAWN_RND = SafeDungeon.getProperty("DungeonSpawnRnd", 25);

		DUNGEON_ITEM_RENEWAL0 = SafeDungeon.getProperty("DungeonRenewalHtml0", 15);
		DUNGEON_ITEM_RENEWAL1 = SafeDungeon.getProperty("DungeonRenewalHtml1", 15);
		DUNGEON_ITEM_RENEWAL2 = SafeDungeon.getProperty("DungeonRenewalHtml2", 15);
		DUNGEON_ITEM_RENEWAL3 = SafeDungeon.getProperty("DungeonRenewalHtml3", 15);
		DUNGEON_ITEM_RENEWAL4 = SafeDungeon.getProperty("DungeonRenewalHtml4", 15);
		DUNGEON_ITEM_RENEWAL5 = SafeDungeon.getProperty("DungeonRenewalHtml5", 15);
		DUNGEON_ITEM_RENEWAL6 = SafeDungeon.getProperty("DungeonRenewalHtml6", 15);
		DUNGEON_ITEM_RENEWAL7 = SafeDungeon.getProperty("DungeonRenewalHtml7", 15);
		DUNGEON_ITEM_RENEWAL8 = SafeDungeon.getProperty("DungeonRenewalHtml8", 15);
		DUNGEON_ITEM_RENEWAL9 = SafeDungeon.getProperty("DungeonRenewalHtml9", 15);
		DUNGEON_ITEM_RENEWAL10 = SafeDungeon.getProperty("DungeonRenewalHtml10", 15);
		DUNGEON_PARTY_ITEM_RENEWAL0 = SafeDungeon.getProperty("DungeonPartyRenewalHtml0", 15);
		DUNGEON_PARTY_ITEM_RENEWAL1 = SafeDungeon.getProperty("DungeonPartyRenewalHtml1", 15);
		DUNGEON_PARTY_ITEM_RENEWAL2 = SafeDungeon.getProperty("DungeonPartyRenewalHtml2", 15);
		DUNGEON_PARTY_ITEM_RENEWAL3 = SafeDungeon.getProperty("DungeonPartyRenewalHtml3", 15);
		DUNGEON_PARTY_ITEM_RENEWAL4 = SafeDungeon.getProperty("DungeonPartyRenewalHtml4", 15);
	}

	// --------------------------------------------------
	// Lineternity paths
	// --------------------------------------------------

	// public static final String ENCHANTING_FILE =
	// "./config/dev/enchanting.properties";
	public static final String DEV_FILE = "./config/dev/lineternity.properties";
	// public static final String SECURITY_FILE =
	// "./config/dev/security.properties";

	// --------------------------------------------------
	// Lineternity settings
	// --------------------------------------------------

	public static String SERVER_NAME;

	public static String SHOP_AIO;

	public static String SHOP_PORTABLE;

	public static String BUFF_PORTABLE;

	public static String TELEPORT_PORTABLE;

	public static boolean ENABLE_RAID_BOSS_DEFEATED_MSG;

	public static boolean ENABLE_MODIFY_SKILL_DURATION;

	public static Map<Integer, Integer> SKILL_DURATION_LIST;

	public static String RAID_BOSS_DEFEATED_BY_CLAN_MEMBER_MSG;

	public static String RAID_BOSS_DEFEATED_BY_PLAYER_MSG;

	public static boolean BANKING_SYSTEM_ENABLED;
	public static int BANKING_SYSTEM_GOLDBARS;
	public static int BANKING_SYSTEM_ADENA;
	public static IntIntHolder[] BANKING_SYSTEM_GOLDCOIN;

	/** Skins */
	public static boolean CMD_SKIN;
	public static int DRESS_SKILL_ID;
	public static int DRESS_SKILL_LVL;

	// --------------------------------------------------
	// Clans settings
	// --------------------------------------------------

	/** Clans */
	public static int ALT_CLAN_JOIN_DAYS;
	public static int ALT_CLAN_CREATE_DAYS;
	public static int ALT_CLAN_DISSOLVE_DAYS;
	public static int ALT_ALLY_JOIN_DAYS_WHEN_LEAVED;
	public static int ALT_ALLY_JOIN_DAYS_WHEN_DISMISSED;
	public static int ALT_ACCEPT_CLAN_DAYS_WHEN_DISMISSED;
	public static int ALT_CREATE_ALLY_DAYS_WHEN_DISSOLVED;
	public static int ALT_MAX_NUM_OF_CLANS_IN_ALLY;
	public static int ALT_CLAN_MEMBERS_FOR_WAR;
	public static int ALT_CLAN_WAR_PENALTY_WHEN_ENDED;
	public static boolean ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH;
	public static int ITEM_ID_BUY_CLAN_HALL;

	/** Manor */
	public static int ALT_MANOR_REFRESH_TIME;
	public static int ALT_MANOR_REFRESH_MIN;
	public static int ALT_MANOR_APPROVE_TIME;
	public static int ALT_MANOR_APPROVE_MIN;
	public static int ALT_MANOR_MAINTENANCE_MIN;
	public static int ALT_MANOR_SAVE_PERIOD_RATE;

	/** Clan Hall function */
	public static long CH_TELE_FEE_RATIO;
	public static int CH_TELE1_FEE;
	public static int CH_TELE2_FEE;
	public static long CH_ITEM_FEE_RATIO;
	public static int CH_ITEM1_FEE;
	public static int CH_ITEM2_FEE;
	public static int CH_ITEM3_FEE;
	public static long CH_MPREG_FEE_RATIO;
	public static int CH_MPREG1_FEE;
	public static int CH_MPREG2_FEE;
	public static int CH_MPREG3_FEE;
	public static int CH_MPREG4_FEE;
	public static int CH_MPREG5_FEE;
	public static long CH_HPREG_FEE_RATIO;
	public static int CH_HPREG1_FEE;
	public static int CH_HPREG2_FEE;
	public static int CH_HPREG3_FEE;
	public static int CH_HPREG4_FEE;
	public static int CH_HPREG5_FEE;
	public static int CH_HPREG6_FEE;
	public static int CH_HPREG7_FEE;
	public static int CH_HPREG8_FEE;
	public static int CH_HPREG9_FEE;
	public static int CH_HPREG10_FEE;
	public static int CH_HPREG11_FEE;
	public static int CH_HPREG12_FEE;
	public static int CH_HPREG13_FEE;
	public static long CH_EXPREG_FEE_RATIO;
	public static int CH_EXPREG1_FEE;
	public static int CH_EXPREG2_FEE;
	public static int CH_EXPREG3_FEE;
	public static int CH_EXPREG4_FEE;
	public static int CH_EXPREG5_FEE;
	public static int CH_EXPREG6_FEE;
	public static int CH_EXPREG7_FEE;
	public static long CH_SUPPORT_FEE_RATIO;
	public static int CH_SUPPORT1_FEE;
	public static int CH_SUPPORT2_FEE;
	public static int CH_SUPPORT3_FEE;
	public static int CH_SUPPORT4_FEE;
	public static int CH_SUPPORT5_FEE;
	public static int CH_SUPPORT6_FEE;
	public static int CH_SUPPORT7_FEE;
	public static int CH_SUPPORT8_FEE;
	public static long CH_CURTAIN_FEE_RATIO;
	public static int CH_CURTAIN1_FEE;
	public static int CH_CURTAIN2_FEE;
	public static long CH_FRONT_FEE_RATIO;
	public static int CH_FRONT1_FEE;
	public static int CH_FRONT2_FEE;

	// --------------------------------------------------
	// Lineternity Events settings
	// --------------------------------------------------

	/** Event Engine settings */
	public static boolean ENABLE_EVENT_ENGINE;
	public static int TIME_BETWEEN_EVENTS;
	public static int NPC_REGISTER;
	public static Location NPC_REGISTER_LOC;
	public static int EVENT_REGISTRATION_TIME;
	public static String[] DOOR_LIST;
	public static byte MIN_LEVEL;
	public static byte MAX_LEVEL;

	/** TvT event settings */
	public static boolean ALLOW_TVT_EVENT;
	public static int TVT_MIN_PLAYERS;
	public static IntIntHolder[] TVT_WINNER_REWARDS;
	public static IntIntHolder[] TVT_DRAW_REWARDS;
	public static int TVT_RUNNING_TIME;
	public static String TVT_TEAM_1_NAME;
	public static int TVT_TEAM_1_COLOR;
	public static Location TVT_TEAM_1_LOCATION;
	public static String TVT_TEAM_2_NAME;
	public static int TVT_TEAM_2_COLOR;
	public static Location TVT_TEAM_2_LOCATION;

	/** Deathmatch event settings */
	public static boolean ALLOW_DM_EVENT;
	public static int DM_MIN_PLAYERS;
	public static IntIntHolder[] DM_ON_KILL_REWARDS;
	public static IntIntHolder[] DM_WINNER_REWARDS;
	public static int DM_RUNNING_TIME;
	public static List<Location> DM_RESPAWN_SPOTS = new ArrayList<>();

	/** CTF event settings */
	public static boolean ALLOW_CTF_EVENT;
	public static int CTF_MIN_PLAYERS;
	public static IntIntHolder[] CTF_ON_SCORE_REWARDS;
	public static IntIntHolder[] CTF_WINNER_REWARDS;
	public static IntIntHolder[] CTF_DRAW_REWARDS;
	public static int CTF_RUNNING_TIME;
	public static String CTF_TEAM_1_NAME;
	public static int CTF_TEAM_1_COLOR;
	public static Location CTF_TEAM_1_LOCATION;
	public static Location CTF_TEAM_1_FLAG_LOCATION;
	public static String CTF_TEAM_2_NAME;
	public static int CTF_TEAM_2_COLOR;
	public static Location CTF_TEAM_2_LOCATION;
	public static Location CTF_TEAM_2_FLAG_LOCATION;

	/** SimonSay event settings */
	public static boolean ALLOW_SIMON_EVENT;
	public static int SIMON_MIN_PLAYERS;
	public static IntIntHolder[] SIMON_WINNER_REWARDS;
	public static int SIMON_ROUND_TIME;
	public static List<Location> SIMON_PLAYER_RESPAWN_SPOTS;
	public static Location SIMON_NPC_RESPAWN_SPOTS;
	public static List<String> SIMON_WORDS;

	/** Pc bang points */
	public static int PCB_INTERVAL;
	public static int PCB_MIN_LEVEL;
	public static int PCB_POINT_MIN;
	public static int PCB_POINT_MAX;
	public static int PCB_CHANCE_DUAL_POINT;
	public static int PCB_AFK_TIMER;
	public static int PCB_Max_POINTS_ALLOWED;
	public static boolean PCB_ENABLED;

	// --------------------------------------------------
	// Default Events settings
	// --------------------------------------------------

	/** Olympiad */
	public static int ALT_OLY_START_TIME;
	public static int ALT_OLY_MIN;
	public static long ALT_OLY_CPERIOD;
	public static long ALT_OLY_BATTLE;
	public static long ALT_OLY_WPERIOD;
	public static long ALT_OLY_VPERIOD;
	public static int ALT_OLY_WAIT_TIME;
	public static int ALT_OLY_WAIT_BATTLE;
	public static int ALT_OLY_WAIT_END;
	public static int ALT_OLY_START_POINTS;
	public static int ALT_OLY_WEEKLY_POINTS;
	public static int ALT_OLY_MIN_MATCHES;
	public static int ALT_OLY_CLASSED;
	public static int ALT_OLY_NONCLASSED;
	public static IntIntHolder[] ALT_OLY_CLASSED_REWARD;
	public static IntIntHolder[] ALT_OLY_NONCLASSED_REWARD;
	public static int ALT_OLY_GP_PER_POINT;
	public static int ALT_OLY_HERO_POINTS;
	public static int ALT_OLY_RANK1_POINTS;
	public static int ALT_OLY_RANK2_POINTS;
	public static int ALT_OLY_RANK3_POINTS;
	public static int ALT_OLY_RANK4_POINTS;
	public static int ALT_OLY_RANK5_POINTS;
	public static int ALT_OLY_MAX_POINTS;
	public static int ALT_OLY_DIVIDER_CLASSED;
	public static int ALT_OLY_DIVIDER_NON_CLASSED;
	public static boolean ALT_OLY_ANNOUNCE_GAMES;
	public static boolean ALT_OLY_ANT_BOOT;
	public static OlympiadPeriod ALT_OLY_PERIOD;
	public static int ALT_OLY_PERIOD_MULTIPLIER;

	/** SevenSigns Festival */
	public static boolean ALT_GAME_CASTLE_DAWN;
	public static boolean ALT_GAME_CASTLE_DUSK;
	public static int ALT_FESTIVAL_MIN_PLAYER;
	public static int ALT_MAXIMUM_PLAYER_CONTRIB;
	public static long ALT_FESTIVAL_MANAGER_START;
	public static long ALT_FESTIVAL_LENGTH;
	public static long ALT_FESTIVAL_CYCLE_LENGTH;
	public static long ALT_FESTIVAL_FIRST_SPAWN;
	public static long ALT_FESTIVAL_FIRST_SWARM;
	public static long ALT_FESTIVAL_SECOND_SPAWN;
	public static long ALT_FESTIVAL_SECOND_SWARM;
	public static long ALT_FESTIVAL_CHEST_SPAWN;

	/** Four Sepulchers */
	public static int FS_TIME_ENTRY;
	public static int FS_TIME_END;
	public static int FS_PARTY_MEMBER_COUNT;

	/** dimensional rift */
	public static int RIFT_MIN_PARTY_SIZE;
	public static int RIFT_SPAWN_DELAY;
	public static int RIFT_MAX_JUMPS;
	public static int RIFT_AUTO_JUMPS_TIME_MIN;
	public static int RIFT_AUTO_JUMPS_TIME_MAX;
	public static int RIFT_ENTER_COST_RECRUIT;
	public static int RIFT_ENTER_COST_SOLDIER;
	public static int RIFT_ENTER_COST_OFFICER;
	public static int RIFT_ENTER_COST_CAPTAIN;
	public static int RIFT_ENTER_COST_COMMANDER;
	public static int RIFT_ENTER_COST_HERO;
	public static double RIFT_BOSS_ROOM_TIME_MUTIPLY;

	/** Wedding system */
	public static boolean ALLOW_WEDDING;
	public static int WEDDING_PRICE;
	public static boolean WEDDING_SAMESEX;
	public static boolean WEDDING_FORMALWEAR;

	/** Lottery */
	public static int ALT_LOTTERY_PRIZE;
	public static int ALT_LOTTERY_TICKET_PRICE;
	public static double ALT_LOTTERY_5_NUMBER_RATE;
	public static double ALT_LOTTERY_4_NUMBER_RATE;
	public static double ALT_LOTTERY_3_NUMBER_RATE;
	public static int ALT_LOTTERY_2_AND_1_NUMBER_PRIZE;

	/** Fishing tournament */
	public static boolean ALT_FISH_CHAMPIONSHIP_ENABLED;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_ITEM;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_1;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_2;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_3;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_4;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_5;

	// --------------------------------------------------
	// GeoEngine
	// --------------------------------------------------

	/** Geodata */
	public static String GEODATA_PATH;

	/** Path checking */
	public static int PART_OF_CHARACTER_HEIGHT;
	public static int MAX_OBSTACLE_HEIGHT;

	/** Path finding */
	public static String PATHFIND_BUFFERS;
	public static int BASE_WEIGHT;
	public static int DIAGONAL_WEIGHT;
	public static int HEURISTIC_WEIGHT;
	public static int OBSTACLE_MULTIPLIER;
	public static int MAX_ITERATIONS;
	public static boolean DEBUG_PATH;
	public static boolean DEBUG_GEO_NODE;

	// --------------------------------------------------
	// HexID
	// --------------------------------------------------

	public static int SERVER_ID;
	public static byte[] HEX_ID;

	// --------------------------------------------------
	// Loginserver
	// --------------------------------------------------

	public static String LOGIN_BIND_ADDRESS;
	public static int PORT_LOGIN;

	public static int LOGIN_TRY_BEFORE_BAN;
	public static int LOGIN_BLOCK_AFTER_BAN;
	public static boolean ACCEPT_NEW_GAMESERVER;

	public static boolean SHOW_LICENCE;

	public static boolean AUTO_CREATE_ACCOUNTS;

	public static boolean LOG_LOGIN_CONTROLLER;

	public static boolean FLOOD_PROTECTION;
	public static int FAST_CONNECTION_LIMIT;
	public static int NORMAL_CONNECTION_TIME;
	public static int FAST_CONNECTION_TIME;
	public static int MAX_CONNECTION_PER_IP;

	// --------------------------------------------------
	// NPCs / Monsters
	// --------------------------------------------------

	/** Champion Mod */
	public static int CHAMPION_FREQUENCY;
	public static int CHAMP_MIN_LVL;
	public static int CHAMP_MAX_LVL;
	public static int CHAMPION_HP;
	public static int CHAMPION_REWARDS;
	public static int CHAMPION_ADENAS_REWARDS;
	public static double CHAMPION_HP_REGEN;
	public static double CHAMPION_ATK;
	public static double CHAMPION_SPD_ATK;
	public static int CHAMPION_REWARD;
	public static int CHAMPION_REWARD_ID;
	public static int CHAMPION_REWARD_QTY;

	/** Buffer */
	public static int BUFFER_MAX_SCHEMES;
	public static int BUFFER_STATIC_BUFF_COST;

	/** Misc */
	public static boolean ALLOW_CLASS_MASTERS;
	public static ClassMasterSettings CLASS_MASTER_SETTINGS;
	public static boolean ALLOW_ENTIRE_TREE;
	public static boolean ANNOUNCE_MAMMON_SPAWN;
	public static boolean ALT_MOB_AGRO_IN_PEACEZONE;
	public static boolean SHOW_NPC_LVL;
	public static boolean SHOW_NPC_CREST;
	public static boolean SHOW_SUMMON_CREST;
	public static boolean ALTERNATE_CLASS_MASTER;

	/** Wyvern Manager */
	public static boolean WYVERN_ALLOW_UPGRADER;
	public static int WYVERN_REQUIRED_LEVEL;
	public static int WYVERN_REQUIRED_CRYSTALS;

	/** Raid Boss */
	public static double RAID_HP_REGEN_MULTIPLIER;
	public static double RAID_MP_REGEN_MULTIPLIER;
	public static double RAID_DEFENCE_MULTIPLIER;
	public static int RAID_MINION_RESPAWN_TIMER;

	public static boolean RAID_DISABLE_CURSE;

	/** Grand Boss */
	public static int SPAWN_INTERVAL_AQ;
	public static int RANDOM_SPAWN_TIME_AQ;

	public static int SPAWN_INTERVAL_ANTHARAS;
	public static int RANDOM_SPAWN_TIME_ANTHARAS;
	public static int WAIT_TIME_ANTHARAS;

	public static int SPAWN_INTERVAL_BAIUM;
	public static int RANDOM_SPAWN_TIME_BAIUM;

	public static int SPAWN_INTERVAL_CORE;
	public static int RANDOM_SPAWN_TIME_CORE;

	public static int SPAWN_INTERVAL_FRINTEZZA;
	public static int RANDOM_SPAWN_TIME_FRINTEZZA;
	public static int WAIT_TIME_FRINTEZZA;

	public static int SPAWN_INTERVAL_ORFEN;
	public static int RANDOM_SPAWN_TIME_ORFEN;

	public static int SPAWN_INTERVAL_SAILREN;
	public static int RANDOM_SPAWN_TIME_SAILREN;
	public static int WAIT_TIME_SAILREN;

	public static int SPAWN_INTERVAL_VALAKAS;
	public static int RANDOM_SPAWN_TIME_VALAKAS;
	public static int WAIT_TIME_VALAKAS;

	public static int SPAWN_INTERVAL_ZAKEN;
	public static int RANDOM_SPAWN_TIME_ZAKEN;

	/** AI */
	public static boolean GUARD_ATTACK_AGGRO_MOB;
	public static int MAX_DRIFT_RANGE;
	public static int MIN_NPC_ANIMATION;
	public static int MAX_NPC_ANIMATION;
	public static int MIN_MONSTER_ANIMATION;
	public static int MAX_MONSTER_ANIMATION;

	// --------------------------------------------------
	// Players
	// --------------------------------------------------

	/** Misc */
	public static boolean EFFECT_CANCELING;
	public static double HP_REGEN_MULTIPLIER;
	public static double MP_REGEN_MULTIPLIER;
	public static double CP_REGEN_MULTIPLIER;
	public static int PLAYER_SPAWN_PROTECTION;
	public static int PLAYER_FAKEDEATH_UP_PROTECTION;
	public static double RESPAWN_RESTORE_HP;
	public static int MAX_PVTSTORE_SLOTS_DWARF;
	public static int MAX_PVTSTORE_SLOTS_OTHER;
	public static boolean DEEPBLUE_DROP_RULES;
	public static boolean ALT_GAME_DELEVEL;
	public static int DEATH_PENALTY_CHANCE;

	/** Inventory & WH */
	public static int INVENTORY_MAXIMUM_NO_DWARF;
	public static int INVENTORY_MAXIMUM_DWARF;
	public static int INVENTORY_MAXIMUM_QUEST_ITEMS;
	public static int INVENTORY_MAXIMUM_PET;
	public static int MAX_ITEM_IN_PACKET;
	public static double ALT_WEIGHT_LIMIT;
	public static int WAREHOUSE_SLOTS_NO_DWARF;
	public static int WAREHOUSE_SLOTS_DWARF;
	public static int WAREHOUSE_SLOTS_CLAN;
	public static int FREIGHT_SLOTS;
	public static boolean ALT_GAME_FREIGHTS;
	public static int ALT_GAME_FREIGHT_PRICE;

	/** Enchant */
	public static double ENCHANT_CHANCE_WEAPON_MAGIC;
	public static double ENCHANT_CHANCE_WEAPON_MAGIC_15PLUS;
	public static double ENCHANT_CHANCE_WEAPON_NONMAGIC;
	public static double ENCHANT_CHANCE_WEAPON_NONMAGIC_15PLUS;
	public static double ENCHANT_CHANCE_ARMOR;
	public static int ENCHANT_MAX_WEAPON;
	public static int ENCHANT_MAX_ARMOR;
	public static int ENCHANT_SAFE_MAX;
	public static int ENCHANT_SAFE_MAX_FULL;

	/** Augmentations */
	public static int AUGMENTATION_NG_SKILL_CHANCE;
	public static int AUGMENTATION_NG_GLOW_CHANCE;
	public static int AUGMENTATION_MID_SKILL_CHANCE;
	public static int AUGMENTATION_MID_GLOW_CHANCE;
	public static int AUGMENTATION_HIGH_SKILL_CHANCE;
	public static int AUGMENTATION_HIGH_GLOW_CHANCE;
	public static int AUGMENTATION_TOP_SKILL_CHANCE;
	public static int AUGMENTATION_TOP_GLOW_CHANCE;
	public static int AUGMENTATION_BASESTAT_CHANCE;

	/** Karma & PvP */
	public static boolean KARMA_PLAYER_CAN_BE_KILLED_IN_PZ;
	public static boolean KARMA_PLAYER_CAN_SHOP;
	public static boolean KARMA_PLAYER_CAN_USE_GK;
	public static boolean KARMA_PLAYER_CAN_TELEPORT;
	public static boolean KARMA_PLAYER_CAN_TRADE;
	public static boolean KARMA_PLAYER_CAN_USE_WH;

	public static boolean KARMA_DROP_GM;
	public static boolean KARMA_AWARD_PK_KILL;
	public static int KARMA_PK_LIMIT;

	public static String KARMA_NONDROPPABLE_PET_ITEMS;
	public static String KARMA_NONDROPPABLE_ITEMS;
	public static int[] KARMA_LIST_NONDROPPABLE_PET_ITEMS;
	public static int[] KARMA_LIST_NONDROPPABLE_ITEMS;

	public static int PVP_NORMAL_TIME;
	public static int PVP_PVP_TIME;

	/** Party */
	public static String PARTY_XP_CUTOFF_METHOD;
	public static int PARTY_XP_CUTOFF_LEVEL;
	public static double PARTY_XP_CUTOFF_PERCENT;
	public static int PARTY_RANGE;

	/** GMs & Admin Stuff */
	public static int DEFAULT_ACCESS_LEVEL;
	public static boolean GM_HERO_AURA;
	public static boolean GM_STARTUP_INVULNERABLE;
	public static boolean GM_STARTUP_INVISIBLE;
	public static boolean GM_STARTUP_SPEED;
	public static boolean GM_STARTUP_SILENCE;
	public static boolean GM_STARTUP_AUTO_LIST;

	/** petitions */
	public static boolean PETITIONING_ALLOWED;
	public static int MAX_PETITIONS_PER_PLAYER;
	public static int MAX_PETITIONS_PENDING;

	/** Crafting **/
	public static boolean IS_CRAFTING_ENABLED;
	public static int DWARF_RECIPE_LIMIT;
	public static int COMMON_RECIPE_LIMIT;
	public static boolean ALT_BLACKSMITH_USE_RECIPES;

	/** Skills & Classes **/
	public static boolean AUTO_LEARN_SKILLS;
	public static boolean MAGIC_FAILURES;
	public static int PERFECT_SHIELD_BLOCK_RATE;
	public static boolean LIFE_CRYSTAL_NEEDED;
	public static boolean SP_BOOK_NEEDED;
	public static boolean ES_SP_BOOK_NEEDED;
	public static boolean DIVINE_SP_BOOK_NEEDED;
	public static boolean SUBCLASS_WITHOUT_QUESTS;
	public static int ALLOWED_SUBCLASS;
	public static boolean ALT_GAME_SUBCLASS_EVERYWHERE;

	/** Buffs */
	public static boolean STORE_SKILL_COOLTIME;
	public static int MAX_BUFFS_AMOUNT;

	/** Messages */
	public static String BOSS_DEFEATED_BY_CLAN_MEMBER_MSG;
	public static String BOSS_DEFEATED_BY_PLAYER_MSG;

	// --------------------------------------------------
	// Sieges
	// --------------------------------------------------

	public static int SIEGE_LENGTH;
	public static int MINIMUM_CLAN_LEVEL;
	public static int MAX_ATTACKERS_NUMBER;
	public static int MAX_DEFENDERS_NUMBER;
	public static int ATTACKERS_RESPAWN_DELAY;
	public static IntIntHolder[] REWARD_WINNER_SIEGE_CLAN;
	public static IntIntHolder[] LEADER_REWARD_WINNER_SIEGE_CLAN;

	// --------------------------------------------------
	// Server
	// --------------------------------------------------

	public static String GAMESERVER_HOSTNAME;
	public static int PORT_GAME;
	public static String HOSTNAME;
	public static int GAME_SERVER_LOGIN_PORT;
	public static String GAME_SERVER_LOGIN_HOST;
	public static int REQUEST_ID;
	public static boolean ACCEPT_ALTERNATE_ID;

	/** Access to database */
	public static String DATABASE_URL;
	public static String DATABASE_LOGIN;
	public static String DATABASE_PASSWORD;
	public static int DATABASE_MAX_CONNECTIONS;

	/** serverList & Test */
	public static boolean SERVER_LIST_BRACKET;
	public static boolean SERVER_LIST_CLOCK;
	public static int SERVER_LIST_AGE;
	public static boolean SERVER_LIST_TESTSERVER;
	public static boolean SERVER_LIST_PVPSERVER;
	public static boolean SERVER_GMONLY;

	/** clients related */
	public static int DELETE_DAYS;
	public static int MAXIMUM_ONLINE_USERS;

	/** Auto-loot */
	public static boolean AUTO_LOOT;
	public static boolean AUTO_LOOT_HERBS;
	public static boolean AUTO_LOOT_RAID;

	/** Items Management */
	public static boolean ALLOW_DISCARDITEM;
	public static boolean MULTIPLE_ITEM_DROP;
	public static int HERB_AUTO_DESTROY_TIME;
	public static int ITEM_AUTO_DESTROY_TIME;
	public static int EQUIPABLE_ITEM_AUTO_DESTROY_TIME;
	public static Map<Integer, Integer> SPECIAL_ITEM_DESTROY_TIME;
	public static int PLAYER_DROPPED_ITEM_MULTIPLIER;

	/** Rate control */
	public static double RATE_XP;
	public static double RATE_SP;
	public static double RATE_PARTY_XP;
	public static double RATE_PARTY_SP;
	public static double RATE_DROP_ADENA;
	public static double RATE_DROP_ITEMS;
	public static double RATE_DROP_ITEMS_BY_RAID;
	public static double RATE_DROP_SPOIL;
	public static int RATE_DROP_MANOR;

	public static double RATE_QUEST_DROP;
	public static double RATE_QUEST_REWARD;
	public static double RATE_QUEST_REWARD_XP;
	public static double RATE_QUEST_REWARD_SP;
	public static double RATE_QUEST_REWARD_ADENA;

	public static double RATE_KARMA_EXP_LOST;
	public static double RATE_SIEGE_GUARDS_PRICE;

	public static int PLAYER_DROP_LIMIT;
	public static int PLAYER_RATE_DROP;
	public static int PLAYER_RATE_DROP_ITEM;
	public static int PLAYER_RATE_DROP_EQUIP;
	public static int PLAYER_RATE_DROP_EQUIP_WEAPON;

	public static int KARMA_DROP_LIMIT;
	public static int KARMA_RATE_DROP;
	public static int KARMA_RATE_DROP_ITEM;
	public static int KARMA_RATE_DROP_EQUIP;
	public static int KARMA_RATE_DROP_EQUIP_WEAPON;

	public static double PET_XP_RATE;
	public static int PET_FOOD_RATE;
	public static double SINEATER_XP_RATE;

	public static double RATE_DROP_COMMON_HERBS;
	public static double RATE_DROP_HP_HERBS;
	public static double RATE_DROP_MP_HERBS;
	public static double RATE_DROP_SPECIAL_HERBS;

	/** Allow types */
	public static boolean ALLOW_FREIGHT;
	public static boolean ALLOW_WAREHOUSE;
	public static boolean ALLOW_WEAR;
	public static int WEAR_DELAY;
	public static int WEAR_PRICE;
	public static boolean ALLOW_LOTTERY;
	public static boolean ALLOW_WATER;
	public static boolean ALLOW_BOAT;
	public static boolean ALLOW_CURSED_WEAPONS;
	public static boolean ALLOW_MANOR;
	public static boolean ENABLE_FALLING_DAMAGE;

	/** Debug & Dev */
	public static boolean ALT_DEV_NO_SPAWNS;
	public static boolean DEVELOPER;
	public static boolean PACKET_HANDLER_DEBUG;
	public static int DEBUG_MOVEMENT;

	/** Deadlock Detector */
	public static boolean DEADLOCK_DETECTOR;
	public static int DEADLOCK_CHECK_INTERVAL;
	public static boolean RESTART_ON_DEADLOCK;

	/** Logs */
	public static boolean LOG_CHAT;
	public static boolean LOG_ITEMS;
	public static boolean GMAUDIT;

	/** Community Board */
	public static boolean ENABLE_COMMUNITY_BOARD;
	public static String BBS_DEFAULT;

	/** Flood Protectors */
	public static int ROLL_DICE_TIME;
	public static int HERO_VOICE_TIME;
	public static int SUBCLASS_TIME;
	public static int DROP_ITEM_TIME;
	public static int SERVER_BYPASS_TIME;
	public static int MULTISELL_TIME;
	public static int MANUFACTURE_TIME;
	public static int MANOR_TIME;
	public static int SENDMAIL_TIME;
	public static int CHARACTER_SELECT_TIME;
	public static int GLOBAL_CHAT_TIME;
	public static int TRADE_CHAT_TIME;
	public static int SOCIAL_TIME;

	/** ThreadPool */
	public static int SCHEDULED_THREAD_POOL_COUNT;
	public static int THREADS_PER_SCHEDULED_THREAD_POOL;
	public static int INSTANT_THREAD_POOL_COUNT;
	public static int THREADS_PER_INSTANT_THREAD_POOL;

	/** Misc */
	public static boolean L2WALKER_PROTECTION;
	public static boolean SERVER_NEWS;
	public static int ZONE_TOWN;
	public static boolean DISABLE_TUTORIAL;
	public static boolean ALLOW_WYVERN_RESTRITION_CITY;

	// --------------------------------------------------
	// Those "hidden" settings haven't configs to avoid admins to fuck their server
	// You still can experiment changing values here. But don't say I didn't warn
	// you.
	// --------------------------------------------------

	/** Reserve Host on LoginServerThread */
	public static boolean RESERVE_HOST_ON_LOGIN = false; // default false

	/** MMO settings */
	public static int MMO_SELECTOR_SLEEP_TIME = 20; // default 20
	public static int MMO_MAX_SEND_PER_PASS = 80; // default 80
	public static int MMO_MAX_READ_PER_PASS = 80; // default 80
	public static int MMO_HELPER_BUFFER_COUNT = 20; // default 20

	/** Client Packets Queue settings */
	public static int CLIENT_PACKET_QUEUE_SIZE = MMO_MAX_READ_PER_PASS + 2; // default MMO_MAX_READ_PER_PASS + 2
	public static int CLIENT_PACKET_QUEUE_MAX_BURST_SIZE = MMO_MAX_READ_PER_PASS + 1; // default MMO_MAX_READ_PER_PASS +
																						// 1
	public static int CLIENT_PACKET_QUEUE_MAX_PACKETS_PER_SECOND = 160; // default 160
	public static int CLIENT_PACKET_QUEUE_MEASURE_INTERVAL = 5; // default 5
	public static int CLIENT_PACKET_QUEUE_MAX_AVERAGE_PACKETS_PER_SECOND = 80; // default 80
	public static int CLIENT_PACKET_QUEUE_MAX_FLOODS_PER_MIN = 2; // default 2
	public static int CLIENT_PACKET_QUEUE_MAX_OVERFLOWS_PER_MIN = 1; // default 1
	public static int CLIENT_PACKET_QUEUE_MAX_UNDERFLOWS_PER_MIN = 1; // default 1
	public static int CLIENT_PACKET_QUEUE_MAX_UNKNOWN_PER_MIN = 5; // default 5

	// --------------------------------------------------

	/**
	 * Initialize {@link ExProperties} from specified configuration file.
	 * 
	 * @param filename : File name to be loaded.
	 * @return ExProperties : Initialized {@link ExProperties}.
	 */
	public static final ExProperties initProperties(String filename) {
		final ExProperties result = new ExProperties();

		try {
			result.load(new File(filename));
		} catch (Exception e) {
			LOGGER.error("An error occured loading '{}' config.", e, filename);
		}

		return result;
	}

	/** Lineternity Loads */

	private static final void loadLineternity() {

		ExProperties lineternity = initProperties("./config/dev/lineternity.properties");

		SERVER_NAME = lineternity.getProperty("ServerName", "Lineternity");
		SHOP_AIO = lineternity.getProperty("SHOPAio", "data/html/dev/cubic/main.htm");
		SHOP_PORTABLE = lineternity.getProperty("SHOPPortable", "data/html/dev/cubic/shopportable/main.htm");
		BUFF_PORTABLE = lineternity.getProperty("BUFFPortable", "data/html/dev/cubic/buffportable/main.htm");
		TELEPORT_PORTABLE = lineternity.getProperty("TELEPORTPortable",
				"data/html/dev/cubic/teleportportable/main.htm");
		ENABLE_MODIFY_SKILL_DURATION = lineternity.getProperty("EnableModifySkillDuration", false);
		if (ENABLE_MODIFY_SKILL_DURATION) {
			String[] propertySplit = lineternity.getProperty("SkillDurationList", "").split(";");
			SKILL_DURATION_LIST = new HashMap<>(propertySplit.length);
			for (String skill : propertySplit) {
				String[] skillSplit = skill.split(",");
				if (skillSplit.length != 2) {
					LOGGER.error("[SkillDurationList]: invalid config property -> SkillDurationList \"" + skill + "\"");
				} else {
					try {
						SKILL_DURATION_LIST.put(Integer.valueOf(Integer.parseInt(skillSplit[0])),
								Integer.valueOf(Integer.parseInt(skillSplit[1])));
					} catch (NumberFormatException nfe) {
						if (!skill.isEmpty())
							LOGGER.error("[SkillDurationList]: invalid config property -> SkillList \"" + skillSplit[0]
									+ "\"" + skillSplit[1]);
					}
				}
			}
		}

		ENABLE_RAID_BOSS_DEFEATED_MSG = lineternity.getProperty("EnableRaidBossDefeatedMsg", false);
		RAID_BOSS_DEFEATED_BY_CLAN_MEMBER_MSG = lineternity.getProperty("RaidBossDefeatedByClanMemberMsg",
				"Raid Boss %raidboss% has been defeated by %player% of clan %clan%.");
		RAID_BOSS_DEFEATED_BY_PLAYER_MSG = lineternity.getProperty("RaidBossDefeatedByPlayerMsg",
				"Raid Boss %raidboss% has been defeated by %player%.");

		ENABLE_MODIFY_SKILL_DURATION = lineternity.getProperty("EnableModifySkillDuration", false);
		if (ENABLE_MODIFY_SKILL_DURATION)

		{
			String[] propertySplit = lineternity.getProperty("SkillDurationList", "").split(";");
			SKILL_DURATION_LIST = new HashMap<>(propertySplit.length);
			for (String skill : propertySplit) {
				String[] skillSplit = skill.split(",");
				if (skillSplit.length != 2) {
					LOGGER.error("[SkillDurationList]: invalid config property -> SkillDurationList \"" + skill + "\"");
				} else {
					try {
						SKILL_DURATION_LIST.put(Integer.valueOf(Integer.parseInt(skillSplit[0])),
								Integer.valueOf(Integer.parseInt(skillSplit[1])));
					} catch (NumberFormatException nfe) {
						if (!skill.isEmpty())
							LOGGER.error("[SkillDurationList]: invalid config property -> SkillList \"" + skillSplit[0]
									+ "\"" + skillSplit[1]);
					}
				}
			}
		}

		BANKING_SYSTEM_ENABLED = lineternity.getProperty("BankingEnabled", false);
		BANKING_SYSTEM_GOLDCOIN = lineternity.parseIntIntList("BankingGoldCoin", "3470-1");
		BANKING_SYSTEM_ADENA = lineternity.getProperty("BankingAdenaCount", 500000000);

		/** Skins */
		CMD_SKIN = Boolean.parseBoolean(lineternity.getProperty("EnableSkinCMD", "True"));
		DRESS_SKILL_ID = Integer.parseInt(lineternity.getProperty("DressSkillId", "0"));
		DRESS_SKILL_LVL = Integer.parseInt(lineternity.getProperty("DressSkillLvl", "0"));

	}

	/* ----------------------- */

	/**
	 * Loads clan and clan hall settings.
	 */
	private static final void loadClans() {
		final ExProperties clans = initProperties(CLANS_FILE);
		ALT_CLAN_JOIN_DAYS = clans.getProperty("DaysBeforeJoinAClan", 5);
		ALT_CLAN_CREATE_DAYS = clans.getProperty("DaysBeforeCreateAClan", 10);
		ALT_MAX_NUM_OF_CLANS_IN_ALLY = clans.getProperty("AltMaxNumOfClansInAlly", 3);
		ALT_CLAN_MEMBERS_FOR_WAR = clans.getProperty("AltClanMembersForWar", 15);
		ALT_CLAN_WAR_PENALTY_WHEN_ENDED = clans.getProperty("AltClanWarPenaltyWhenEnded", 5);
		ALT_CLAN_DISSOLVE_DAYS = clans.getProperty("DaysToPassToDissolveAClan", 7);
		ALT_ALLY_JOIN_DAYS_WHEN_LEAVED = clans.getProperty("DaysBeforeJoinAllyWhenLeaved", 1);
		ALT_ALLY_JOIN_DAYS_WHEN_DISMISSED = clans.getProperty("DaysBeforeJoinAllyWhenDismissed", 1);
		ALT_ACCEPT_CLAN_DAYS_WHEN_DISMISSED = clans.getProperty("DaysBeforeAcceptNewClanWhenDismissed", 1);
		ALT_CREATE_ALLY_DAYS_WHEN_DISSOLVED = clans.getProperty("DaysBeforeCreateNewAllyWhenDissolved", 10);
		ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH = clans.getProperty("AltMembersCanWithdrawFromClanWH", false);
		ITEM_ID_BUY_CLAN_HALL = clans.getProperty("ItemIDBuyClanHall", 57);

		ALT_MANOR_REFRESH_TIME = clans.getProperty("AltManorRefreshTime", 20);
		ALT_MANOR_REFRESH_MIN = clans.getProperty("AltManorRefreshMin", 0);
		ALT_MANOR_APPROVE_TIME = clans.getProperty("AltManorApproveTime", 6);
		ALT_MANOR_APPROVE_MIN = clans.getProperty("AltManorApproveMin", 0);
		ALT_MANOR_MAINTENANCE_MIN = clans.getProperty("AltManorMaintenanceMin", 6);
		ALT_MANOR_SAVE_PERIOD_RATE = clans.getProperty("AltManorSavePeriodRate", 2) * 3600000;

		CH_TELE_FEE_RATIO = clans.getProperty("ClanHallTeleportFunctionFeeRatio", 86400000);
		CH_TELE1_FEE = clans.getProperty("ClanHallTeleportFunctionFeeLvl1", 7000);
		CH_TELE2_FEE = clans.getProperty("ClanHallTeleportFunctionFeeLvl2", 14000);
		CH_SUPPORT_FEE_RATIO = clans.getProperty("ClanHallSupportFunctionFeeRatio", 86400000);
		CH_SUPPORT1_FEE = clans.getProperty("ClanHallSupportFeeLvl1", 17500);
		CH_SUPPORT2_FEE = clans.getProperty("ClanHallSupportFeeLvl2", 35000);
		CH_SUPPORT3_FEE = clans.getProperty("ClanHallSupportFeeLvl3", 49000);
		CH_SUPPORT4_FEE = clans.getProperty("ClanHallSupportFeeLvl4", 77000);
		CH_SUPPORT5_FEE = clans.getProperty("ClanHallSupportFeeLvl5", 147000);
		CH_SUPPORT6_FEE = clans.getProperty("ClanHallSupportFeeLvl6", 252000);
		CH_SUPPORT7_FEE = clans.getProperty("ClanHallSupportFeeLvl7", 259000);
		CH_SUPPORT8_FEE = clans.getProperty("ClanHallSupportFeeLvl8", 364000);
		CH_MPREG_FEE_RATIO = clans.getProperty("ClanHallMpRegenerationFunctionFeeRatio", 86400000);
		CH_MPREG1_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl1", 14000);
		CH_MPREG2_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl2", 26250);
		CH_MPREG3_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl3", 45500);
		CH_MPREG4_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl4", 96250);
		CH_MPREG5_FEE = clans.getProperty("ClanHallMpRegenerationFeeLvl5", 140000);
		CH_HPREG_FEE_RATIO = clans.getProperty("ClanHallHpRegenerationFunctionFeeRatio", 86400000);
		CH_HPREG1_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl1", 4900);
		CH_HPREG2_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl2", 5600);
		CH_HPREG3_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl3", 7000);
		CH_HPREG4_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl4", 8166);
		CH_HPREG5_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl5", 10500);
		CH_HPREG6_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl6", 12250);
		CH_HPREG7_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl7", 14000);
		CH_HPREG8_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl8", 15750);
		CH_HPREG9_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl9", 17500);
		CH_HPREG10_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl10", 22750);
		CH_HPREG11_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl11", 26250);
		CH_HPREG12_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl12", 29750);
		CH_HPREG13_FEE = clans.getProperty("ClanHallHpRegenerationFeeLvl13", 36166);
		CH_EXPREG_FEE_RATIO = clans.getProperty("ClanHallExpRegenerationFunctionFeeRatio", 86400000);
		CH_EXPREG1_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl1", 21000);
		CH_EXPREG2_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl2", 42000);
		CH_EXPREG3_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl3", 63000);
		CH_EXPREG4_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl4", 105000);
		CH_EXPREG5_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl5", 147000);
		CH_EXPREG6_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl6", 163331);
		CH_EXPREG7_FEE = clans.getProperty("ClanHallExpRegenerationFeeLvl7", 210000);
		CH_ITEM_FEE_RATIO = clans.getProperty("ClanHallItemCreationFunctionFeeRatio", 86400000);
		CH_ITEM1_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl1", 210000);
		CH_ITEM2_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl2", 490000);
		CH_ITEM3_FEE = clans.getProperty("ClanHallItemCreationFunctionFeeLvl3", 980000);
		CH_CURTAIN_FEE_RATIO = clans.getProperty("ClanHallCurtainFunctionFeeRatio", 86400000);
		CH_CURTAIN1_FEE = clans.getProperty("ClanHallCurtainFunctionFeeLvl1", 2002);
		CH_CURTAIN2_FEE = clans.getProperty("ClanHallCurtainFunctionFeeLvl2", 2625);
		CH_FRONT_FEE_RATIO = clans.getProperty("ClanHallFrontPlatformFunctionFeeRatio", 86400000);
		CH_FRONT1_FEE = clans.getProperty("ClanHallFrontPlatformFunctionFeeLvl1", 3031);
		CH_FRONT2_FEE = clans.getProperty("ClanHallFrontPlatformFunctionFeeLvl2", 9331);
	}

	/**
	 * Loads event settings.<br>
	 * Such as olympiad, seven signs festival, four sepulchures, dimensional rift,
	 * weddings, lottery, fishing championship.
	 */
	private static final void loadEvents() {

		// LINETERNITY NFT
		// -------------------------------------------------
		// Events Load
		// -------------------------------------------------

		final ExProperties lineternityEvents = initProperties(LINETERNITY_EVENTS);
		ENABLE_EVENT_ENGINE = lineternityEvents.getProperty("Enableevents", false);
		TIME_BETWEEN_EVENTS = lineternityEvents.getProperty("TimeBetweenEvents", 60);
		EVENT_REGISTRATION_TIME = lineternityEvents.getProperty("EventRegistrationTime", 10);
		DOOR_LIST = lineternityEvents.getProperty("ListDoors", "24190002;24190003").split(";");
		NPC_REGISTER = lineternityEvents.getProperty("NpcRegisterId", 50018);
		NPC_REGISTER_LOC = lineternityEvents.parseLocation("NpcRegisterLoc", "151808,46864,-3408");
		MIN_LEVEL = (byte) lineternityEvents.getProperty("MinLevel", 40);
		MAX_LEVEL = (byte) lineternityEvents.getProperty("MaxLevel", 80);

		// -------------------------------------------------
		// PC Bang Point
		// -------------------------------------------------

		PCB_INTERVAL = lineternityEvents.getProperty("PcBangPointTime", 0);
		PCB_MIN_LEVEL = lineternityEvents.getProperty("PcBangPointMinLevel", 20);
		PCB_POINT_MIN = lineternityEvents.getProperty("PcBangPointMinCount", 1);
		PCB_POINT_MAX = lineternityEvents.getProperty("PcBangPointMaxCount", 5);
		PCB_CHANCE_DUAL_POINT = lineternityEvents.getProperty("PcBangPointDualChance", 100);
		PCB_AFK_TIMER = lineternityEvents.getProperty("PcBangTimerAFK", 1);
		PCB_Max_POINTS_ALLOWED = lineternityEvents.getProperty("PcBangMaxPointsAllowed", 200000);
		PCB_ENABLED = lineternityEvents.getProperty("PcBangEnabled", true);

		// -------------------------------------------------
		// TvT
		// -------------------------------------------------

		ALLOW_TVT_EVENT = lineternityEvents.getProperty("AllowTvTEvent", false);
		TVT_MIN_PLAYERS = lineternityEvents.getProperty("TvTMinPlayers", 2);

		TVT_WINNER_REWARDS = lineternityEvents.parseIntIntList("TvTWinnerRewards", "57,1");
		TVT_DRAW_REWARDS = lineternityEvents.parseIntIntList("TvTDrawRewards", "57,1");

		TVT_RUNNING_TIME = lineternityEvents.getProperty("TvTRunningTime", 10);
		TVT_TEAM_1_NAME = lineternityEvents.getProperty("TvTTeam1Name", "Orange");
		TVT_TEAM_1_COLOR = Integer.decode("0x" + lineternityEvents.getProperty("TvTTeam1Color", "4499FF"));
		TVT_TEAM_1_LOCATION = lineternityEvents.parseLocation("TvTTeam1Location", "0,0,0");
		TVT_TEAM_2_NAME = lineternityEvents.getProperty("TvTTeam2Name", "Green");
		TVT_TEAM_2_COLOR = Integer.decode("0x" + lineternityEvents.getProperty("TvTTeam2Color", "00FF00"));
		TVT_TEAM_2_LOCATION = lineternityEvents.parseLocation("TvTTeam2Location", "0,0,0");

		// -------------------------------------------------
		// DM
		// -------------------------------------------------

		ALLOW_DM_EVENT = lineternityEvents.getProperty("AllowDMEvent", false);
		DM_MIN_PLAYERS = lineternityEvents.getProperty("DMMinPlayers", 2);

		DM_ON_KILL_REWARDS = lineternityEvents.parseIntIntList("DMOnKillRewards", "57,1");
		DM_WINNER_REWARDS = lineternityEvents.parseIntIntList("DMWinnerRewards", "57,1");

		DM_RUNNING_TIME = lineternityEvents.getProperty("DMRunningTime", 10);
		String dm_resp_spots = lineternityEvents.getProperty("DMRespawnSpots", "0,0,0;0,0,0");
		String[] dm_resp_spots_split = dm_resp_spots.split(";");
		for (String s : dm_resp_spots_split) {
			String[] ss = s.split(",");
			DM_RESPAWN_SPOTS
					.add(new Location(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2])));
		}

		// -------------------------------------------------
		// CTF
		// -------------------------------------------------
		ALLOW_CTF_EVENT = lineternityEvents.getProperty("AllowCTFEvent", false);
		CTF_MIN_PLAYERS = lineternityEvents.getProperty("CTFMinPlayers", 2);

		CTF_ON_SCORE_REWARDS = lineternityEvents.parseIntIntList("CTFOnScoreRewards", "57,1");
		CTF_WINNER_REWARDS = lineternityEvents.parseIntIntList("CTFWinnerRewards", "57,1");
		CTF_DRAW_REWARDS = lineternityEvents.parseIntIntList("CTFDrawRewards", "57,1");

		CTF_RUNNING_TIME = lineternityEvents.getProperty("CTFRunningTime", 10);
		CTF_TEAM_1_NAME = lineternityEvents.getProperty("CTFTeam1Name", "Orange");
		CTF_TEAM_1_COLOR = Integer.decode("0x" + lineternityEvents.getProperty("CTFTeam1Color", "4499FF"));
		CTF_TEAM_1_LOCATION = lineternityEvents.parseLocation("CTFTeam1Location", "0,0,0");
		CTF_TEAM_1_FLAG_LOCATION = lineternityEvents.parseLocation("CTFTeam1FlagLocation", "0,0,0");
		CTF_TEAM_2_NAME = lineternityEvents.getProperty("CTFTeam2Name", "Green");
		CTF_TEAM_2_COLOR = Integer.decode("0x" + lineternityEvents.getProperty("CTFTeam2Color", "00FF00"));
		CTF_TEAM_2_LOCATION = lineternityEvents.parseLocation("CTFTeam2Location", "0,0,0");
		CTF_TEAM_2_FLAG_LOCATION = lineternityEvents.parseLocation("CTFTeam2FlagLocation", "0,0,0");

		// -------------------------------------------------
		// SimonSay
		// -------------------------------------------------
		ALLOW_SIMON_EVENT = lineternityEvents.getProperty("AllowSimonSaysEvent", false);
		SIMON_MIN_PLAYERS = lineternityEvents.getProperty("SimonMinPlayers", 2);

		SIMON_WINNER_REWARDS = lineternityEvents.parseIntIntList("SimonWinnerRewards", "57-1");
		SIMON_ROUND_TIME = lineternityEvents.getProperty("SimonRoundTime", 30);

		SIMON_PLAYER_RESPAWN_SPOTS = new ArrayList<>();
		String[] simon_player_respawn_splots_split = lineternityEvents
				.getProperty("SimonPlayerRespawnSpots", "0,0,0;0,0,0").split(";");
		for (String s : simon_player_respawn_splots_split) {
			String[] ss = s.split(",");
			SIMON_PLAYER_RESPAWN_SPOTS
					.add(new Location(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2])));
		}

		String[] simon_npc_respawn_splots_split = lineternityEvents.getProperty("SimonNpcRespawnSpots", "0,0,0")
				.split(",");
		SIMON_NPC_RESPAWN_SPOTS = new Location(Integer.parseInt(simon_npc_respawn_splots_split[0]),
				Integer.parseInt(simon_npc_respawn_splots_split[1]),
				Integer.parseInt(simon_npc_respawn_splots_split[2]));

		SIMON_WORDS = new ArrayList<>();
		for (String word : lineternityEvents.getProperty("SimonWordsToSay", "word1;word2").split(";"))
			SIMON_WORDS.add(word);

		// --------------------------------------------------

		final ExProperties events = initProperties(EVENTS_FILE);
		ALT_OLY_START_TIME = lineternityEvents.getProperty("AltOlyStartTime", 18);
		ALT_OLY_MIN = events.getProperty("AltOlyMin", 0);
		ALT_OLY_CPERIOD = events.getProperty("AltOlyCPeriod", 21600000);
		ALT_OLY_BATTLE = events.getProperty("AltOlyBattle", 180000);
		ALT_OLY_WPERIOD = events.getProperty("AltOlyWPeriod", 604800000);
		ALT_OLY_VPERIOD = events.getProperty("AltOlyVPeriod", 86400000);
		ALT_OLY_WAIT_TIME = events.getProperty("AltOlyWaitTime", 30);
		ALT_OLY_WAIT_BATTLE = events.getProperty("AltOlyWaitBattle", 60);
		ALT_OLY_WAIT_END = events.getProperty("AltOlyWaitEnd", 40);
		ALT_OLY_START_POINTS = events.getProperty("AltOlyStartPoints", 18);
		ALT_OLY_WEEKLY_POINTS = events.getProperty("AltOlyWeeklyPoints", 3);
		ALT_OLY_MIN_MATCHES = events.getProperty("AltOlyMinMatchesToBeClassed", 5);
		ALT_OLY_CLASSED = events.getProperty("AltOlyClassedParticipants", 5);
		ALT_OLY_NONCLASSED = events.getProperty("AltOlyNonClassedParticipants", 9);
		ALT_OLY_CLASSED_REWARD = events.parseIntIntList("AltOlyClassedReward", "6651-50");
		ALT_OLY_NONCLASSED_REWARD = events.parseIntIntList("AltOlyNonClassedReward", "6651-30");
		ALT_OLY_GP_PER_POINT = events.getProperty("AltOlyGPPerPoint", 1000);
		ALT_OLY_HERO_POINTS = events.getProperty("AltOlyHeroPoints", 300);
		ALT_OLY_RANK1_POINTS = events.getProperty("AltOlyRank1Points", 100);
		ALT_OLY_RANK2_POINTS = events.getProperty("AltOlyRank2Points", 75);
		ALT_OLY_RANK3_POINTS = events.getProperty("AltOlyRank3Points", 55);
		ALT_OLY_RANK4_POINTS = events.getProperty("AltOlyRank4Points", 40);
		ALT_OLY_RANK5_POINTS = events.getProperty("AltOlyRank5Points", 30);
		ALT_OLY_MAX_POINTS = events.getProperty("AltOlyMaxPoints", 10);
		ALT_OLY_DIVIDER_CLASSED = events.getProperty("AltOlyDividerClassed", 3);
		ALT_OLY_DIVIDER_NON_CLASSED = events.getProperty("AltOlyDividerNonClassed", 5);
		ALT_OLY_ANNOUNCE_GAMES = events.getProperty("AltOlyAnnounceGames", true);
		ALT_OLY_ANT_BOOT = events.getProperty("AltOlyAntBoot", false);
		ALT_OLY_PERIOD = OlympiadPeriod.valueOf(events.getProperty("AltOlyPeriod", "MONTH"));
		ALT_OLY_PERIOD_MULTIPLIER = events.getProperty("AltOlyPeriodMultiplier", 1);

		ALT_GAME_CASTLE_DAWN = events.getProperty("AltCastleForDawn", true);
		ALT_GAME_CASTLE_DUSK = events.getProperty("AltCastleForDusk", true);
		ALT_FESTIVAL_MIN_PLAYER = MathUtil.limit(events.getProperty("AltFestivalMinPlayer", 5), 2, 9);
		ALT_MAXIMUM_PLAYER_CONTRIB = events.getProperty("AltMaxPlayerContrib", 1000000);
		ALT_FESTIVAL_MANAGER_START = events.getProperty("AltFestivalManagerStart", 120000);
		ALT_FESTIVAL_LENGTH = events.getProperty("AltFestivalLength", 1080000);
		ALT_FESTIVAL_CYCLE_LENGTH = events.getProperty("AltFestivalCycleLength", 2280000);
		ALT_FESTIVAL_FIRST_SPAWN = events.getProperty("AltFestivalFirstSpawn", 120000);
		ALT_FESTIVAL_FIRST_SWARM = events.getProperty("AltFestivalFirstSwarm", 300000);
		ALT_FESTIVAL_SECOND_SPAWN = events.getProperty("AltFestivalSecondSpawn", 540000);
		ALT_FESTIVAL_SECOND_SWARM = events.getProperty("AltFestivalSecondSwarm", 720000);
		ALT_FESTIVAL_CHEST_SPAWN = events.getProperty("AltFestivalChestSpawn", 900000);

		FS_TIME_ENTRY = events.getProperty("EntryTime", 55);
		FS_TIME_END = events.getProperty("EndTime", 50);
		FS_PARTY_MEMBER_COUNT = MathUtil.limit(events.getProperty("NeededPartyMembers", 4), 2, 9);

		RIFT_MIN_PARTY_SIZE = events.getProperty("RiftMinPartySize", 2);
		RIFT_MAX_JUMPS = events.getProperty("MaxRiftJumps", 4);
		RIFT_SPAWN_DELAY = events.getProperty("RiftSpawnDelay", 10000);
		RIFT_AUTO_JUMPS_TIME_MIN = events.getProperty("AutoJumpsDelayMin", 480);
		RIFT_AUTO_JUMPS_TIME_MAX = events.getProperty("AutoJumpsDelayMax", 600);
		RIFT_ENTER_COST_RECRUIT = events.getProperty("RecruitCost", 18);
		RIFT_ENTER_COST_SOLDIER = events.getProperty("SoldierCost", 21);
		RIFT_ENTER_COST_OFFICER = events.getProperty("OfficerCost", 24);
		RIFT_ENTER_COST_CAPTAIN = events.getProperty("CaptainCost", 27);
		RIFT_ENTER_COST_COMMANDER = events.getProperty("CommanderCost", 30);
		RIFT_ENTER_COST_HERO = events.getProperty("HeroCost", 33);
		RIFT_BOSS_ROOM_TIME_MUTIPLY = events.getProperty("BossRoomTimeMultiply", 1.);

		ALLOW_WEDDING = events.getProperty("AllowWedding", false);
		WEDDING_PRICE = events.getProperty("WeddingPrice", 1000000);
		WEDDING_SAMESEX = events.getProperty("WeddingAllowSameSex", false);
		WEDDING_FORMALWEAR = events.getProperty("WeddingFormalWear", true);

		ALT_LOTTERY_PRIZE = events.getProperty("AltLotteryPrize", 50000);
		ALT_LOTTERY_TICKET_PRICE = events.getProperty("AltLotteryTicketPrice", 2000);
		ALT_LOTTERY_5_NUMBER_RATE = events.getProperty("AltLottery5NumberRate", 0.6);
		ALT_LOTTERY_4_NUMBER_RATE = events.getProperty("AltLottery4NumberRate", 0.2);
		ALT_LOTTERY_3_NUMBER_RATE = events.getProperty("AltLottery3NumberRate", 0.2);
		ALT_LOTTERY_2_AND_1_NUMBER_PRIZE = events.getProperty("AltLottery2and1NumberPrize", 200);

		ALT_FISH_CHAMPIONSHIP_ENABLED = events.getProperty("AltFishChampionshipEnabled", true);
		ALT_FISH_CHAMPIONSHIP_REWARD_ITEM = events.getProperty("AltFishChampionshipRewardItemId", 57);
		ALT_FISH_CHAMPIONSHIP_REWARD_1 = events.getProperty("AltFishChampionshipReward1", 800000);
		ALT_FISH_CHAMPIONSHIP_REWARD_2 = events.getProperty("AltFishChampionshipReward2", 500000);
		ALT_FISH_CHAMPIONSHIP_REWARD_3 = events.getProperty("AltFishChampionshipReward3", 300000);
		ALT_FISH_CHAMPIONSHIP_REWARD_4 = events.getProperty("AltFishChampionshipReward4", 200000);
		ALT_FISH_CHAMPIONSHIP_REWARD_5 = events.getProperty("AltFishChampionshipReward5", 100000);
	}

	/**
	 * Loads geoengine settings.
	 */
	private static final void loadGeoengine() {
		final ExProperties geoengine = initProperties(GEOENGINE_FILE);
		GEODATA_PATH = geoengine.getProperty("GeoDataPath", "./data/geodata/");

		PART_OF_CHARACTER_HEIGHT = geoengine.getProperty("PartOfCharacterHeight", 75);
		MAX_OBSTACLE_HEIGHT = geoengine.getProperty("MaxObstacleHeight", 32);

		PATHFIND_BUFFERS = geoengine.getProperty("PathFindBuffers", "100x6;128x6;192x6;256x4;320x4;384x4;500x2");
		BASE_WEIGHT = geoengine.getProperty("BaseWeight", 10);
		DIAGONAL_WEIGHT = geoengine.getProperty("DiagonalWeight", 14);
		OBSTACLE_MULTIPLIER = geoengine.getProperty("ObstacleMultiplier", 10);
		HEURISTIC_WEIGHT = geoengine.getProperty("HeuristicWeight", 20);
		MAX_ITERATIONS = geoengine.getProperty("MaxIterations", 3500);
		DEBUG_PATH = geoengine.getProperty("DebugPath", false);
		DEBUG_GEO_NODE = geoengine.getProperty("DebugGeoNode", false);
	}

	/**
	 * Loads hex ID settings.
	 */
	private static final void loadHexID() {
		final ExProperties hexid = initProperties(HEXID_FILE);
		SERVER_ID = Integer.parseInt(hexid.getProperty("ServerID"));
		HEX_ID = new BigInteger(hexid.getProperty("HexID"), 16).toByteArray();
	}

	/**
	 * Saves hex ID file.
	 * 
	 * @param serverId : The ID of server.
	 * @param hexId    : The hex ID of server.
	 */
	public static final void saveHexid(int serverId, String hexId) {
		saveHexid(serverId, hexId, HEXID_FILE);
	}

	/**
	 * Saves hexID file.
	 * 
	 * @param serverId : The ID of server.
	 * @param hexId    : The hexID of server.
	 * @param filename : The file name.
	 */
	public static final void saveHexid(int serverId, String hexId, String filename) {
		try {
			final File file = new File(filename);
			file.createNewFile();

			final Properties hexSetting = new Properties();
			hexSetting.setProperty("ServerID", String.valueOf(serverId));
			hexSetting.setProperty("HexID", hexId);

			try (OutputStream out = new FileOutputStream(file)) {
				hexSetting.store(out, "the hexID to auth into login");
			}
		} catch (Exception e) {
			LOGGER.error("Failed to save hex ID to '{}' file.", e, filename);
		}
	}

	/**
	 * Loads NPC settings.<br>
	 * Such as champion monsters, NPC buffer, class master, wyvern, raid bosses and
	 * grand bosses, AI.
	 */
	private static final void loadNpcs() {
		final ExProperties npcs = initProperties(NPCS_FILE);
		CHAMPION_FREQUENCY = npcs.getProperty("ChampionFrequency", 0);
		CHAMP_MIN_LVL = npcs.getProperty("ChampionMinLevel", 20);
		CHAMP_MAX_LVL = npcs.getProperty("ChampionMaxLevel", 70);
		CHAMPION_HP = npcs.getProperty("ChampionHp", 8);
		CHAMPION_HP_REGEN = npcs.getProperty("ChampionHpRegen", 1.);
		CHAMPION_REWARDS = npcs.getProperty("ChampionRewards", 8);
		CHAMPION_ADENAS_REWARDS = npcs.getProperty("ChampionAdenasRewards", 1);
		CHAMPION_ATK = npcs.getProperty("ChampionAtk", 1.);
		CHAMPION_SPD_ATK = npcs.getProperty("ChampionSpdAtk", 1.);
		CHAMPION_REWARD = npcs.getProperty("ChampionRewardItem", 0);
		CHAMPION_REWARD_ID = npcs.getProperty("ChampionRewardItemID", 6393);
		CHAMPION_REWARD_QTY = npcs.getProperty("ChampionRewardItemQty", 1);

		BUFFER_MAX_SCHEMES = npcs.getProperty("BufferMaxSchemesPerChar", 4);
		BUFFER_STATIC_BUFF_COST = npcs.getProperty("BufferStaticCostPerBuff", -1);

		ALLOW_CLASS_MASTERS = npcs.getProperty("AllowClassMasters", false);
		ALLOW_ENTIRE_TREE = npcs.getProperty("AllowEntireTree", false);
		if (ALLOW_CLASS_MASTERS)
			CLASS_MASTER_SETTINGS = new ClassMasterSettings(npcs.getProperty("ConfigClassMaster"));

		ANNOUNCE_MAMMON_SPAWN = npcs.getProperty("AnnounceMammonSpawn", true);
		ALT_MOB_AGRO_IN_PEACEZONE = npcs.getProperty("AltMobAgroInPeaceZone", true);
		SHOW_NPC_LVL = npcs.getProperty("ShowNpcLevel", false);
		SHOW_NPC_CREST = npcs.getProperty("ShowNpcCrest", false);
		SHOW_SUMMON_CREST = npcs.getProperty("ShowSummonCrest", false);
		ALTERNATE_CLASS_MASTER = npcs.getProperty("AlternateClassMaster", false);

		WYVERN_ALLOW_UPGRADER = npcs.getProperty("AllowWyvernUpgrader", true);
		WYVERN_REQUIRED_LEVEL = npcs.getProperty("RequiredStriderLevel", 55);
		WYVERN_REQUIRED_CRYSTALS = npcs.getProperty("RequiredCrystalsNumber", 10);

		RAID_HP_REGEN_MULTIPLIER = npcs.getProperty("RaidHpRegenMultiplier", 1.);
		RAID_MP_REGEN_MULTIPLIER = npcs.getProperty("RaidMpRegenMultiplier", 1.);
		RAID_DEFENCE_MULTIPLIER = npcs.getProperty("RaidDefenceMultiplier", 1.);
		RAID_MINION_RESPAWN_TIMER = npcs.getProperty("RaidMinionRespawnTime", 300000);

		RAID_DISABLE_CURSE = npcs.getProperty("DisableRaidCurse", false);

		SPAWN_INTERVAL_AQ = npcs.getProperty("AntQueenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_AQ = npcs.getProperty("AntQueenRandomSpawn", 17);

		SPAWN_INTERVAL_ANTHARAS = npcs.getProperty("AntharasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_ANTHARAS = npcs.getProperty("AntharasRandomSpawn", 72);
		WAIT_TIME_ANTHARAS = npcs.getProperty("AntharasWaitTime", 30) * 60000;

		SPAWN_INTERVAL_BAIUM = npcs.getProperty("BaiumSpawnInterval", 168);
		RANDOM_SPAWN_TIME_BAIUM = npcs.getProperty("BaiumRandomSpawn", 48);

		SPAWN_INTERVAL_CORE = npcs.getProperty("CoreSpawnInterval", 60);
		RANDOM_SPAWN_TIME_CORE = npcs.getProperty("CoreRandomSpawn", 23);

		SPAWN_INTERVAL_FRINTEZZA = npcs.getProperty("FrintezzaSpawnInterval", 48);
		RANDOM_SPAWN_TIME_FRINTEZZA = npcs.getProperty("FrintezzaRandomSpawn", 8);
		WAIT_TIME_FRINTEZZA = npcs.getProperty("FrintezzaWaitTime", 1) * 60000;

		SPAWN_INTERVAL_ORFEN = npcs.getProperty("OrfenSpawnInterval", 48);
		RANDOM_SPAWN_TIME_ORFEN = npcs.getProperty("OrfenRandomSpawn", 20);

		SPAWN_INTERVAL_SAILREN = npcs.getProperty("SailrenSpawnInterval", 36);
		RANDOM_SPAWN_TIME_SAILREN = npcs.getProperty("SailrenRandomSpawn", 24);
		WAIT_TIME_SAILREN = npcs.getProperty("SailrenWaitTime", 5) * 60000;

		SPAWN_INTERVAL_VALAKAS = npcs.getProperty("ValakasSpawnInterval", 264);
		RANDOM_SPAWN_TIME_VALAKAS = npcs.getProperty("ValakasRandomSpawn", 72);
		WAIT_TIME_VALAKAS = npcs.getProperty("ValakasWaitTime", 30) * 60000;

		SPAWN_INTERVAL_ZAKEN = npcs.getProperty("ZakenSpawnInterval", 60);
		RANDOM_SPAWN_TIME_ZAKEN = npcs.getProperty("ZakenRandomSpawn", 20);

		GUARD_ATTACK_AGGRO_MOB = npcs.getProperty("GuardAttackAggroMob", false);
		MAX_DRIFT_RANGE = npcs.getProperty("MaxDriftRange", 300);
		MIN_NPC_ANIMATION = npcs.getProperty("MinNPCAnimation", 20);
		MAX_NPC_ANIMATION = npcs.getProperty("MaxNPCAnimation", 40);
		MIN_MONSTER_ANIMATION = npcs.getProperty("MinMonsterAnimation", 10);
		MAX_MONSTER_ANIMATION = npcs.getProperty("MaxMonsterAnimation", 40);
	}

	/**
	 * Loads player settings.<br>
	 * Such as stats, inventory/warehouse, enchant, augmentation, karma, party,
	 * admin, petition, skill learn.
	 */
	private static final void loadPlayers() {
		final ExProperties players = initProperties(PLAYERS_FILE);
		EFFECT_CANCELING = players.getProperty("CancelLesserEffect", true);
		HP_REGEN_MULTIPLIER = players.getProperty("HpRegenMultiplier", 1.);
		MP_REGEN_MULTIPLIER = players.getProperty("MpRegenMultiplier", 1.);
		CP_REGEN_MULTIPLIER = players.getProperty("CpRegenMultiplier", 1.);
		PLAYER_SPAWN_PROTECTION = players.getProperty("PlayerSpawnProtection", 0);
		PLAYER_FAKEDEATH_UP_PROTECTION = players.getProperty("PlayerFakeDeathUpProtection", 0);
		RESPAWN_RESTORE_HP = players.getProperty("RespawnRestoreHP", 0.7);
		MAX_PVTSTORE_SLOTS_DWARF = players.getProperty("MaxPvtStoreSlotsDwarf", 5);
		MAX_PVTSTORE_SLOTS_OTHER = players.getProperty("MaxPvtStoreSlotsOther", 4);
		DEEPBLUE_DROP_RULES = players.getProperty("UseDeepBlueDropRules", true);
		ALT_GAME_DELEVEL = players.getProperty("Delevel", true);
		DEATH_PENALTY_CHANCE = players.getProperty("DeathPenaltyChance", 20);

		INVENTORY_MAXIMUM_NO_DWARF = players.getProperty("MaximumSlotsForNoDwarf", 80);
		INVENTORY_MAXIMUM_DWARF = players.getProperty("MaximumSlotsForDwarf", 100);
		INVENTORY_MAXIMUM_QUEST_ITEMS = players.getProperty("MaximumSlotsForQuestItems", 100);
		INVENTORY_MAXIMUM_PET = players.getProperty("MaximumSlotsForPet", 12);
		MAX_ITEM_IN_PACKET = Math.max(INVENTORY_MAXIMUM_NO_DWARF, INVENTORY_MAXIMUM_DWARF);
		ALT_WEIGHT_LIMIT = players.getProperty("AltWeightLimit", 1);
		WAREHOUSE_SLOTS_NO_DWARF = players.getProperty("MaximumWarehouseSlotsForNoDwarf", 100);
		WAREHOUSE_SLOTS_DWARF = players.getProperty("MaximumWarehouseSlotsForDwarf", 120);
		WAREHOUSE_SLOTS_CLAN = players.getProperty("MaximumWarehouseSlotsForClan", 150);
		FREIGHT_SLOTS = players.getProperty("MaximumFreightSlots", 20);
		ALT_GAME_FREIGHTS = players.getProperty("AltGameFreights", false);
		ALT_GAME_FREIGHT_PRICE = players.getProperty("AltGameFreightPrice", 1000);

		ENCHANT_CHANCE_WEAPON_MAGIC = players.getProperty("EnchantChanceMagicWeapon", 0.4);
		ENCHANT_CHANCE_WEAPON_MAGIC_15PLUS = players.getProperty("EnchantChanceMagicWeapon15Plus", 0.2);
		ENCHANT_CHANCE_WEAPON_NONMAGIC = players.getProperty("EnchantChanceNonMagicWeapon", 0.7);
		ENCHANT_CHANCE_WEAPON_NONMAGIC_15PLUS = players.getProperty("EnchantChanceNonMagicWeapon15Plus", 0.35);
		ENCHANT_CHANCE_ARMOR = players.getProperty("EnchantChanceArmor", 0.66);
		ENCHANT_MAX_WEAPON = players.getProperty("EnchantMaxWeapon", 0);
		ENCHANT_MAX_ARMOR = players.getProperty("EnchantMaxArmor", 0);
		ENCHANT_SAFE_MAX = players.getProperty("EnchantSafeMax", 3);
		ENCHANT_SAFE_MAX_FULL = players.getProperty("EnchantSafeMaxFull", 4);

		AUGMENTATION_NG_SKILL_CHANCE = players.getProperty("AugmentationNGSkillChance", 15);
		AUGMENTATION_NG_GLOW_CHANCE = players.getProperty("AugmentationNGGlowChance", 0);
		AUGMENTATION_MID_SKILL_CHANCE = players.getProperty("AugmentationMidSkillChance", 30);
		AUGMENTATION_MID_GLOW_CHANCE = players.getProperty("AugmentationMidGlowChance", 40);
		AUGMENTATION_HIGH_SKILL_CHANCE = players.getProperty("AugmentationHighSkillChance", 45);
		AUGMENTATION_HIGH_GLOW_CHANCE = players.getProperty("AugmentationHighGlowChance", 70);
		AUGMENTATION_TOP_SKILL_CHANCE = players.getProperty("AugmentationTopSkillChance", 60);
		AUGMENTATION_TOP_GLOW_CHANCE = players.getProperty("AugmentationTopGlowChance", 100);
		AUGMENTATION_BASESTAT_CHANCE = players.getProperty("AugmentationBaseStatChance", 1);

		KARMA_PLAYER_CAN_BE_KILLED_IN_PZ = players.getProperty("KarmaPlayerCanBeKilledInPeaceZone", false);
		KARMA_PLAYER_CAN_SHOP = players.getProperty("KarmaPlayerCanShop", false);
		KARMA_PLAYER_CAN_USE_GK = players.getProperty("KarmaPlayerCanUseGK", false);
		KARMA_PLAYER_CAN_TELEPORT = players.getProperty("KarmaPlayerCanTeleport", true);
		KARMA_PLAYER_CAN_TRADE = players.getProperty("KarmaPlayerCanTrade", true);
		KARMA_PLAYER_CAN_USE_WH = players.getProperty("KarmaPlayerCanUseWareHouse", true);
		KARMA_DROP_GM = players.getProperty("CanGMDropEquipment", false);
		KARMA_AWARD_PK_KILL = players.getProperty("AwardPKKillPVPPoint", true);
		KARMA_PK_LIMIT = players.getProperty("MinimumPKRequiredToDrop", 5);
		KARMA_NONDROPPABLE_PET_ITEMS = players.getProperty("ListOfPetItems",
				"2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650");
		KARMA_NONDROPPABLE_ITEMS = players.getProperty("ListOfNonDroppableItemsForPK",
				"1147,425,1146,461,10,2368,7,6,2370,2369");

		String[] array = KARMA_NONDROPPABLE_PET_ITEMS.split(",");
		KARMA_LIST_NONDROPPABLE_PET_ITEMS = new int[array.length];

		for (int i = 0; i < array.length; i++)
			KARMA_LIST_NONDROPPABLE_PET_ITEMS[i] = Integer.parseInt(array[i]);

		array = KARMA_NONDROPPABLE_ITEMS.split(",");
		KARMA_LIST_NONDROPPABLE_ITEMS = new int[array.length];

		for (int i = 0; i < array.length; i++)
			KARMA_LIST_NONDROPPABLE_ITEMS[i] = Integer.parseInt(array[i]);

		// sorting so binarySearch can be used later
		Arrays.sort(KARMA_LIST_NONDROPPABLE_PET_ITEMS);
		Arrays.sort(KARMA_LIST_NONDROPPABLE_ITEMS);

		PVP_NORMAL_TIME = players.getProperty("PvPVsNormalTime", 15000);
		PVP_PVP_TIME = players.getProperty("PvPVsPvPTime", 30000);

		PARTY_XP_CUTOFF_METHOD = players.getProperty("PartyXpCutoffMethod", "level");
		PARTY_XP_CUTOFF_PERCENT = players.getProperty("PartyXpCutoffPercent", 3.);
		PARTY_XP_CUTOFF_LEVEL = players.getProperty("PartyXpCutoffLevel", 20);
		PARTY_RANGE = players.getProperty("PartyRange", 1500);

		DEFAULT_ACCESS_LEVEL = players.getProperty("DefaultAccessLevel", 0);
		GM_HERO_AURA = players.getProperty("GMHeroAura", false);
		GM_STARTUP_INVULNERABLE = players.getProperty("GMStartupInvulnerable", true);
		GM_STARTUP_SPEED = players.getProperty("GMStartupSpeed", true);
		GM_STARTUP_INVISIBLE = players.getProperty("GMStartupInvisible", true);
		GM_STARTUP_SILENCE = players.getProperty("GMStartupSilence", true);
		GM_STARTUP_AUTO_LIST = players.getProperty("GMStartupAutoList", true);

		PETITIONING_ALLOWED = players.getProperty("PetitioningAllowed", true);
		MAX_PETITIONS_PER_PLAYER = players.getProperty("MaxPetitionsPerPlayer", 5);
		MAX_PETITIONS_PENDING = players.getProperty("MaxPetitionsPending", 25);

		IS_CRAFTING_ENABLED = players.getProperty("CraftingEnabled", true);
		DWARF_RECIPE_LIMIT = players.getProperty("DwarfRecipeLimit", 50);
		COMMON_RECIPE_LIMIT = players.getProperty("CommonRecipeLimit", 50);
		ALT_BLACKSMITH_USE_RECIPES = players.getProperty("AltBlacksmithUseRecipes", true);

		AUTO_LEARN_SKILLS = players.getProperty("AutoLearnSkills", false);
		MAGIC_FAILURES = players.getProperty("MagicFailures", true);
		PERFECT_SHIELD_BLOCK_RATE = players.getProperty("PerfectShieldBlockRate", 5);
		LIFE_CRYSTAL_NEEDED = players.getProperty("LifeCrystalNeeded", true);
		SP_BOOK_NEEDED = players.getProperty("SpBookNeeded", true);
		ES_SP_BOOK_NEEDED = players.getProperty("EnchantSkillSpBookNeeded", true);
		DIVINE_SP_BOOK_NEEDED = players.getProperty("DivineInspirationSpBookNeeded", true);
		SUBCLASS_WITHOUT_QUESTS = players.getProperty("SubClassWithoutQuests", false);
		ALLOWED_SUBCLASS = players.getProperty("AllowedSubclass", 3);
		ALT_GAME_SUBCLASS_EVERYWHERE = players.getProperty("AltSubclassEverywhere", false);

		MAX_BUFFS_AMOUNT = players.getProperty("MaxBuffsAmount", 20);
		STORE_SKILL_COOLTIME = players.getProperty("StoreSkillCooltime", true);

		BOSS_DEFEATED_BY_CLAN_MEMBER_MSG = players.getProperty("BossDefeatedByClanMemberMsg",
				"Raid Boss %raidboss% has been defeated by %player% of clan %clan%.");
		BOSS_DEFEATED_BY_PLAYER_MSG = players.getProperty("BossDefeatedByPlayerMsg",
				"Raid Boss %raidboss% has been defeated by %player%.");

	}

	/**
	 * Loads siege settings.
	 */
	private static final void loadSieges() {
		final ExProperties sieges = initProperties(Config.SIEGE_FILE);

		SIEGE_LENGTH = sieges.getProperty("SiegeLength", 120);
		MINIMUM_CLAN_LEVEL = sieges.getProperty("SiegeClanMinLevel", 4);
		MAX_ATTACKERS_NUMBER = sieges.getProperty("AttackerMaxClans", 10);
		MAX_DEFENDERS_NUMBER = sieges.getProperty("DefenderMaxClans", 10);
		ATTACKERS_RESPAWN_DELAY = sieges.getProperty("AttackerRespawn", 10000);
		REWARD_WINNER_SIEGE_CLAN = sieges.parseIntIntList("MembersRewardsID", "57-100");
		LEADER_REWARD_WINNER_SIEGE_CLAN = sieges.parseIntIntList("LeaderRewardsID", "57-400");
	}

	/**
	 * Loads gameserver settings.<br>
	 * IP addresses, database, rates, feature enabled/disabled, misc.
	 */
	private static final void loadServer() {
		final ExProperties server = initProperties(SERVER_FILE);

		GAMESERVER_HOSTNAME = server.getProperty("GameserverHostname");
		PORT_GAME = server.getProperty("GameserverPort", 7777);

		HOSTNAME = server.getProperty("Hostname", "*");

		GAME_SERVER_LOGIN_PORT = server.getProperty("LoginPort", 9014);
		GAME_SERVER_LOGIN_HOST = server.getProperty("LoginHost", "127.0.0.1");

		REQUEST_ID = server.getProperty("RequestServerID", 0);
		ACCEPT_ALTERNATE_ID = server.getProperty("AcceptAlternateID", true);

		DATABASE_URL = server.getProperty("URL", System.getenv("DATABASE_URI_GAME"));
		DATABASE_LOGIN = server.getProperty("Login", System.getenv("DATABASE_URI_USER"));
		DATABASE_PASSWORD = server.getProperty("Password", System.getenv("DATABASE_URI_PASS"));
		DATABASE_MAX_CONNECTIONS = server.getProperty("MaximumDbConnections", 10);

		SERVER_LIST_BRACKET = server.getProperty("ServerListBrackets", false);
		SERVER_LIST_CLOCK = server.getProperty("ServerListClock", false);
		SERVER_GMONLY = server.getProperty("ServerGMOnly", false);
		SERVER_LIST_AGE = server.getProperty("ServerListAgeLimit", 0);
		SERVER_LIST_TESTSERVER = server.getProperty("TestServer", false);
		SERVER_LIST_PVPSERVER = server.getProperty("PvpServer", true);

		DELETE_DAYS = server.getProperty("DeleteCharAfterDays", 7);
		MAXIMUM_ONLINE_USERS = server.getProperty("MaximumOnlineUsers", 100);

		AUTO_LOOT = server.getProperty("AutoLoot", false);
		AUTO_LOOT_HERBS = server.getProperty("AutoLootHerbs", false);
		AUTO_LOOT_RAID = server.getProperty("AutoLootRaid", false);

		ALLOW_DISCARDITEM = server.getProperty("AllowDiscardItem", true);
		MULTIPLE_ITEM_DROP = server.getProperty("MultipleItemDrop", true);
		HERB_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyHerbTime", 15) * 1000;
		ITEM_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyItemTime", 600) * 1000;
		EQUIPABLE_ITEM_AUTO_DESTROY_TIME = server.getProperty("AutoDestroyEquipableItemTime", 0) * 1000;
		SPECIAL_ITEM_DESTROY_TIME = new HashMap<>();
		String[] data = server.getProperty("AutoDestroySpecialItemTime", (String[]) null, ",");
		if (data != null) {
			for (String itemData : data) {
				String[] item = itemData.split("-");
				SPECIAL_ITEM_DESTROY_TIME.put(Integer.parseInt(item[0]), Integer.parseInt(item[1]) * 1000);
			}
		}
		PLAYER_DROPPED_ITEM_MULTIPLIER = server.getProperty("PlayerDroppedItemMultiplier", 1);

		RATE_XP = server.getProperty("RateXp", 1.);
		RATE_SP = server.getProperty("RateSp", 1.);
		RATE_PARTY_XP = server.getProperty("RatePartyXp", 1.);
		RATE_PARTY_SP = server.getProperty("RatePartySp", 1.);
		RATE_DROP_ADENA = server.getProperty("RateDropAdena", 1.);
		RATE_DROP_ITEMS = server.getProperty("RateDropItems", 1.);
		RATE_DROP_ITEMS_BY_RAID = server.getProperty("RateRaidDropItems", 1.);
		RATE_DROP_SPOIL = server.getProperty("RateDropSpoil", 1.);
		RATE_DROP_MANOR = server.getProperty("RateDropManor", 1);
		RATE_QUEST_DROP = server.getProperty("RateQuestDrop", 1.);
		RATE_QUEST_REWARD = server.getProperty("RateQuestReward", 1.);
		RATE_QUEST_REWARD_XP = server.getProperty("RateQuestRewardXP", 1.);
		RATE_QUEST_REWARD_SP = server.getProperty("RateQuestRewardSP", 1.);
		RATE_QUEST_REWARD_ADENA = server.getProperty("RateQuestRewardAdena", 1.);
		RATE_KARMA_EXP_LOST = server.getProperty("RateKarmaExpLost", 1.);
		RATE_SIEGE_GUARDS_PRICE = server.getProperty("RateSiegeGuardsPrice", 1.);
		RATE_DROP_COMMON_HERBS = server.getProperty("RateCommonHerbs", 1.);
		RATE_DROP_HP_HERBS = server.getProperty("RateHpHerbs", 1.);
		RATE_DROP_MP_HERBS = server.getProperty("RateMpHerbs", 1.);
		RATE_DROP_SPECIAL_HERBS = server.getProperty("RateSpecialHerbs", 1.);
		PLAYER_DROP_LIMIT = server.getProperty("PlayerDropLimit", 3);
		PLAYER_RATE_DROP = server.getProperty("PlayerRateDrop", 5);
		PLAYER_RATE_DROP_ITEM = server.getProperty("PlayerRateDropItem", 70);
		PLAYER_RATE_DROP_EQUIP = server.getProperty("PlayerRateDropEquip", 25);
		PLAYER_RATE_DROP_EQUIP_WEAPON = server.getProperty("PlayerRateDropEquipWeapon", 5);
		PET_XP_RATE = server.getProperty("PetXpRate", 1.);
		PET_FOOD_RATE = server.getProperty("PetFoodRate", 1);
		SINEATER_XP_RATE = server.getProperty("SinEaterXpRate", 1.);
		KARMA_DROP_LIMIT = server.getProperty("KarmaDropLimit", 10);
		KARMA_RATE_DROP = server.getProperty("KarmaRateDrop", 70);
		KARMA_RATE_DROP_ITEM = server.getProperty("KarmaRateDropItem", 50);
		KARMA_RATE_DROP_EQUIP = server.getProperty("KarmaRateDropEquip", 40);
		KARMA_RATE_DROP_EQUIP_WEAPON = server.getProperty("KarmaRateDropEquipWeapon", 10);

		ALLOW_FREIGHT = server.getProperty("AllowFreight", true);
		ALLOW_WAREHOUSE = server.getProperty("AllowWarehouse", true);
		ALLOW_WEAR = server.getProperty("AllowWear", true);
		WEAR_DELAY = server.getProperty("WearDelay", 5);
		WEAR_PRICE = server.getProperty("WearPrice", 10);
		ALLOW_LOTTERY = server.getProperty("AllowLottery", true);
		ALLOW_WATER = server.getProperty("AllowWater", true);
		ALLOW_MANOR = server.getProperty("AllowManor", true);
		ALLOW_BOAT = server.getProperty("AllowBoat", true);
		ALLOW_CURSED_WEAPONS = server.getProperty("AllowCursedWeapons", true);

		ENABLE_FALLING_DAMAGE = server.getProperty("EnableFallingDamage", true);

		ALT_DEV_NO_SPAWNS = server.getProperty("NoSpawns", false);
		DEVELOPER = server.getProperty("Developer", false);
		PACKET_HANDLER_DEBUG = server.getProperty("PacketHandlerDebug", false);
		DEBUG_MOVEMENT = server.getProperty("DebugMovement", 0) * 1000;

		DEADLOCK_DETECTOR = server.getProperty("DeadLockDetector", false);
		DEADLOCK_CHECK_INTERVAL = server.getProperty("DeadLockCheckInterval", 20);
		RESTART_ON_DEADLOCK = server.getProperty("RestartOnDeadlock", false);

		LOG_CHAT = server.getProperty("LogChat", false);
		LOG_ITEMS = server.getProperty("LogItems", false);
		GMAUDIT = server.getProperty("GMAudit", false);

		ENABLE_COMMUNITY_BOARD = server.getProperty("EnableCommunityBoard", false);
		BBS_DEFAULT = server.getProperty("BBSDefault", "_bbshome");

		ROLL_DICE_TIME = server.getProperty("RollDiceTime", 4200);
		HERO_VOICE_TIME = server.getProperty("HeroVoiceTime", 10000);
		SUBCLASS_TIME = server.getProperty("SubclassTime", 2000);
		DROP_ITEM_TIME = server.getProperty("DropItemTime", 1000);
		SERVER_BYPASS_TIME = server.getProperty("ServerBypassTime", 500);
		MULTISELL_TIME = server.getProperty("MultisellTime", 100);
		MANUFACTURE_TIME = server.getProperty("ManufactureTime", 300);
		MANOR_TIME = server.getProperty("ManorTime", 3000);
		SENDMAIL_TIME = server.getProperty("SendMailTime", 10000);
		CHARACTER_SELECT_TIME = server.getProperty("CharacterSelectTime", 3000);
		GLOBAL_CHAT_TIME = server.getProperty("GlobalChatTime", 0);
		TRADE_CHAT_TIME = server.getProperty("TradeChatTime", 0);
		SOCIAL_TIME = server.getProperty("SocialTime", 2000);

		SCHEDULED_THREAD_POOL_COUNT = server.getProperty("ScheduledThreadPoolCount", -1);
		THREADS_PER_SCHEDULED_THREAD_POOL = server.getProperty("ThreadsPerScheduledThreadPool", 4);
		INSTANT_THREAD_POOL_COUNT = server.getProperty("InstantThreadPoolCount", -1);
		THREADS_PER_INSTANT_THREAD_POOL = server.getProperty("ThreadsPerInstantThreadPool", 2);

		L2WALKER_PROTECTION = server.getProperty("L2WalkerProtection", false);
		ZONE_TOWN = server.getProperty("ZoneTown", 0);
		SERVER_NEWS = server.getProperty("ShowServerNews", false);
		DISABLE_TUTORIAL = server.getProperty("DisableTutorial", false);

		ALLOW_WYVERN_RESTRITION_CITY = server.getProperty("MountRequest", false);
	}

	/**
	 * Loads loginserver settings.<br>
	 * IP addresses, database, account, misc.
	 */
	private static final void loadLogin() {
		final ExProperties login = initProperties(LOGIN_CONFIGURATION_FILE);
		HOSTNAME = login.getProperty("Hostname", "localhost");

		LOGIN_BIND_ADDRESS = login.getProperty("LoginserverHostname", "*");
		PORT_LOGIN = login.getProperty("LoginserverPort", 2106);

		GAME_SERVER_LOGIN_HOST = login.getProperty("LoginHostname", "*");
		GAME_SERVER_LOGIN_PORT = login.getProperty("LoginPort", 9014);

		LOGIN_TRY_BEFORE_BAN = login.getProperty("LoginTryBeforeBan", 3);
		LOGIN_BLOCK_AFTER_BAN = login.getProperty("LoginBlockAfterBan", 600);
		ACCEPT_NEW_GAMESERVER = login.getProperty("AcceptNewGameServer", false);

		SHOW_LICENCE = login.getProperty("ShowLicence", true);

		DATABASE_URL = login.getProperty("URL", System.getenv("DATABASE_URI_AUTH"));
		DATABASE_LOGIN = login.getProperty("Login", System.getenv("DATABASE_URI_USER"));
		DATABASE_PASSWORD = login.getProperty("Password", System.getenv("DATABASE_URI_PASS"));
		DATABASE_MAX_CONNECTIONS = login.getProperty("MaximumDbConnections", 10);

		AUTO_CREATE_ACCOUNTS = login.getProperty("AutoCreateAccounts", true);

		LOG_LOGIN_CONTROLLER = login.getProperty("LogLoginController", false);

		FLOOD_PROTECTION = login.getProperty("EnableFloodProtection", true);
		FAST_CONNECTION_LIMIT = login.getProperty("FastConnectionLimit", 15);
		NORMAL_CONNECTION_TIME = login.getProperty("NormalConnectionTime", 700);
		FAST_CONNECTION_TIME = login.getProperty("FastConnectionTime", 350);
		MAX_CONNECTION_PER_IP = login.getProperty("MaxConnectionPerIP", 50);
	}

	public static final void loadGameServer() {
		LOGGER.info("Loading gameserver configuration files.");

		// lineternity setting
		loadLineternity();

		// clans settings
		loadClans();

		// events settings
		loadEvents();

		// geoengine settings
		loadGeoengine();

		// hexID
		loadHexID();

		// NPCs/monsters settings
		loadNpcs();

		// players settings
		loadPlayers();

		// siege settings
		loadSieges();

		// server settings
		loadServer();

		// event settings
		loadDungeon();
	}

	public static final void loadLoginServer() {
		LOGGER.info("Loading loginserver configuration files.");

		// login settings
		loadLogin();
	}

	public static final void loadAccountManager() {
		LOGGER.info("Loading account manager configuration files.");

		// login settings
		loadLogin();
	}

	public static final void loadGameServerRegistration() {
		LOGGER.info("Loading gameserver registration configuration files.");

		// login settings
		loadLogin();
	}

	public static final void loadGeodataConverter() {
		LOGGER.info("Loading geodata converter configuration files.");

		// geoengine settings
		loadGeoengine();
	}

	public static final class ClassMasterSettings {
		private final Map<Integer, Boolean> _allowedClassChange;
		private final Map<Integer, List<IntIntHolder>> _claimItems;
		private final Map<Integer, List<IntIntHolder>> _rewardItems;

		public ClassMasterSettings(String configLine) {
			_allowedClassChange = new HashMap<>(3);
			_claimItems = new HashMap<>(3);
			_rewardItems = new HashMap<>(3);

			if (configLine != null)
				parseConfigLine(configLine.trim());
		}

		private void parseConfigLine(String configLine) {
			StringTokenizer st = new StringTokenizer(configLine, ";");
			while (st.hasMoreTokens()) {
				// Get allowed class change.
				int job = Integer.parseInt(st.nextToken());

				_allowedClassChange.put(job, true);

				List<IntIntHolder> items = new ArrayList<>();

				// Parse items needed for class change.
				if (st.hasMoreTokens()) {
					StringTokenizer st2 = new StringTokenizer(st.nextToken(), "[],");
					while (st2.hasMoreTokens()) {
						StringTokenizer st3 = new StringTokenizer(st2.nextToken(), "()");
						items.add(
								new IntIntHolder(Integer.parseInt(st3.nextToken()), Integer.parseInt(st3.nextToken())));
					}
				}

				// Feed the map, and clean the list.
				_claimItems.put(job, items);
				items = new ArrayList<>();

				// Parse gifts after class change.
				if (st.hasMoreTokens()) {
					StringTokenizer st2 = new StringTokenizer(st.nextToken(), "[],");
					while (st2.hasMoreTokens()) {
						StringTokenizer st3 = new StringTokenizer(st2.nextToken(), "()");
						items.add(
								new IntIntHolder(Integer.parseInt(st3.nextToken()), Integer.parseInt(st3.nextToken())));
					}
				}

				_rewardItems.put(job, items);
			}
		}

		public boolean isAllowed(int job) {
			if (_allowedClassChange == null)
				return false;

			if (_allowedClassChange.containsKey(job))
				return _allowedClassChange.get(job);

			return false;
		}

		public List<IntIntHolder> getRewardItems(int job) {
			return _rewardItems.get(job);
		}

		public List<IntIntHolder> getRequiredItems(int job) {
			return _claimItems.get(job);
		}
	}
}
