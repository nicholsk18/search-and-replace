/**
 * This class takes a path in
 * recursively checks for sub directories
 * puts all the files in to ArrayList
 * Returns the arrays list of all the paths in that directory and subdirectories (Might have StackOverfloError if deep enough)
 */


import java.io.File;
import java.util.ArrayList;

public class FilePath {
    private String startPath;
    private String fileFolder;
    private ArrayList<String> paths;

    public FilePath(String startPath){
        this.startPath = startPath;
        this.fileFolder = "";
        this.paths = new ArrayList<>();
    }

    public ArrayList<String> getPaths(){
        // list all the files in start path
        File[] files = new File(this.startPath).listFiles();
        // this method adds file paths to array list
        makePath(files);

        // return new paths
        return this.paths;
    }

    // Makes changes to files
    public void makePath(File[] files){

        for(File file : files){
            if(file.isDirectory()){
                System.out.println("Directory: " + file.getName());
                this.fileFolder = file.getName();
                makePath(file.listFiles());

                // clear the fileFolder from previous folder
                this.fileFolder = "";
            } else {

                // if has a folder add folder to path
                // other wise exclude the folder
                if (!this.fileFolder.isEmpty()){
                    paths.add(this.startPath  + this.fileFolder + "/" + file.getName());
                } else {
                    paths.add(this.startPath + file.getName());
                }
            }
        }
    }

}






//    private String[] folderNames = new String[] {"admanagement/", "blogs/", "business/", "calendar/", "customersupport/", "dbtools/", "ecommerce/", "educational/", "email/", "erp/", "filemanager/", "forums/", "frameworks/", "game/", "guestbook/", "imagegalleries/", "libraries/", "misc-app/", "music/", "poll/", "portalcms/", "projectmanagement/", "rss/", "shoppingcart/", "socialnetworking/", "video/", "wikis/"};
//    private String[][] fileNames = new String[][] {
//            {"osclass.php", "reviveadserver.php", "yclas.php"},
//            {"antville.php", "b2evolution.php", "cadmus.php", "chyrp.php", "dotclear.php", "drupal.php", "flatpress.php", "hotglue.php", "joomla.php", "lifetype.php", "moveabletype.php", "nibbleblog.php", "pubvana.php", "serendipity.php", "statusnet.php", "textpattern.php"},
//            {"firefly.php", "fusio.php", "glpi.php", "java.php", "linux.php", "mautic.php", "mysql.php", "perl.php", "php.php", "python.php", "seopanel.php", "slims.php", "soholaunch.php", "spip.php", "tastyigniter.php", "wallabag.php", "yourls.php"},
//            {"booked.php", "luxcal.php", "supercali.php", "webcalendar.php"},
//            {"attendize.php", "blabax.php", "faveohelpdesk.php", "freescout.php", "handesk.php", "helpdezk.php", "hesk.php", "livehelperchat.php", "maiansupport.php", "mibewmessenger.php", "opensupports.php", "osticket.php", "phpmyfaq.php", "salessyntax.php", "visionhelpdesk.php"},
//            {"adminer.php", "phpliteadmin.php", "phpmyadmin.php", "sidu.php", "sqlitemanager.php", "xcloner.php"},
//            {"abantecart.php", "alegrocart.php", "arastta.php", "blesta.php", "boxbilling.php", "clientexec.php", "eccube.php", "invoiceninja.php", "invoiceplane.php", "litecart.php", "loadedcommerce.php", "maian.php", "opensourcepos.php", "oscommerce.php", "peelshopping.php", "quickcart.php", "seotoaster.php", "shopsite.php", "shopware.php", "thelia2.php", "thirtybees.php", "webid.php", "whatacart.php", "xcart.php", "zencart.php", "zeuscart.php"},
//            {"chamilo.php", "claroline.php", "elabftw.php", "formalms.php", "gibbon.php", "ilias.php", "moodle.php", "omeka.php", "opensis.php", "savsoftquiz.php", "tcexam.php", "xerte.php"},
//            {"dadamail.php", "phplist.php", "rainloop.php", "roundcube.php", "webinstamaillist.php", "webmail.php"},
//            {"churchcrm.php", "elycharts.php", "espocrm.php", "groupoffice.php", "icehrm.php", "infinitewp.php", "iqdesk.php", "jorani.php", "librehealthehr.php", "privacypolicygenerator.php", "sentrifugo.php", "simplerisk.php", "tine20.php", "zdoo.php"},
//            {"arfoo.php", "dropzonejs.php", "extplorer.php", "filerun.php", "letodms.php", "monstaftp.php", "net2ftp.php", "nextcloud.php", "opendocman.php", "owncloud.php", "paste.php", "phpld.php", "unmark.php"},
//            {"bbpress.php", "elkarte.php", "fluxbb.php", "formtools.php", "layerbb.php", "minibb.php", "mybb.php", "mylittleforum.php", "phpbb.php", "phpformgenerator.php", "smf.php", "vanilla.php", "vbulletin.php", "webtrees.php", "xenforo.php", "xmb.php"},
//            {"angularjs.php", "bootstrap.php", "cakephp.php", "codeigniter.php", "dhtmlx.php", "dojo.php", "fuelphp.php", "laravel.php", "phpdocumentor.php", "prado.php", "smarty.php", "symfony.php", "uikit.php", "userspice.php", "webasyst.php", "yii.php", "zend.php"},
//            {"blacknova.php"},
//            {"advancedguestbook.php", "bellabook.php", "ricargbook.php", "vxguestbook.php"},
//            {"cheveretofree.php", "coopermine.php", "igalerie.php", "lychee.php", "piwigo.php", "tinywebgallery.php", "zenphoto.php"},
//            {"flot.php", "jqplot.php", "jquery.php", "jsmorph.php", "jszip.php", "less.php", "mochikit.php", "modernizr.php", "mootools.php", "paintbrushjs.php", "phplot.php", "prototype.php", "raphael.php", "scriptaculous.php", "scripty2.php", "sizzle.php", "vuejs.php", "yui.php"},
//            {"apiq.php", "apostrophe.php", "dantestories.php", "expressa.php", "ghost.php", "hexo.php", "keystonejs.php", "neos.php", "noddity.php", "noosfero.php", "plume.php", "postleaf.php", "propertywebbuilder.php", "publify.php", "strapi.php", "wagtail.php"},
//            {"ampache.php", "kplaylist.php", "podcastgenerator.php"},
//            {"aardvarktopsites.php", "cjdynamicpoll.php", "framadate.php", "limesurvey.php", "littlepoll.php", "littlesoftwarestats.php", "logaholic.php", "matomo.php", "openwebanalytics.php", "phpesp.php", "question2answer.php"},
//            {"alfresco.php", "anchor.php", "apprain.php", "atlantis.php", "backdropcms.php", "baun.php", "bigtreecms.php", "bludit.php", "bolt.php", "cmsimple.php", "cmsmadesimple.php", "cockpit.php", "commentics.php", "composr.php", "concrete5.php", "contao.php", "couchcms.php", "croogo.php", "cszcms.php", "directus.php", "e107.php", "fiyo.php", "fork.php", "geeklog.php", "getsimplecms.php", "grav.php", "humogen.php", "impresscms.php", "impresspages.php", "jamroom.php", "jcore.php", "kirbycms.php", "koken.php", "kopage.php", "lepton.php", "livesite.php", "mahara.php", "mambo.php", "microweber.php", "modx.php", "monstra.php", "octobercms.php", "openbusinesscard.php", "openrealestate.php", "pagekit.php", "phpfusion.php", "phpnuke.php", "phpwcms.php", "pico.php", "pimcore.php", "plikli.php", "plone.php", "pluck.php", "pluxml.php", "popojicms.php", "precurio.php", "processwire.php", "pyrocms.php", "quickcms.php", "razorcms.php", "redaxo.php", "redaxscript.php", "roadiz.php", "saurus.php", "schlix.php", "silex.php", "silverstripe.php", "sitecake.php", "sitemagic.php", "snews.php", "squidex.php", "subrion.php", "tikiwiki.php", "typesetter.php", "typo3.php", "umbraco.php", "wbce.php", "websitebaker.php", "wondercms.php", "xoops.php", "zikula.php", "zsite.php"},
//            {"bugs.php", "collabtive.php", "domainmod.php", "dotproject.php", "easyappointment.php", "eventum.php", "eyeos.php", "fengoffice.php", "flyspray.php", "kanboard.php", "kimai.php", "mantisbt.php", "openconference.php", "openjournal.php", "openmonographpress.php", "phpcollab.php", "projeqtor.php", "qdpm.php", "rukovoditel.php", "snipeit.php", "soplanning.php", "taskfreak.php", "testlink.php", "thebuggenie.php", "traq.php", "webcollab.php", "zentao.php"},
//            {"freshrss.php", "miniflux.php", "selfoss.php", "simplepie.php", "tinytinyrss.php"},
//            {"cubecart.php", "magento.php", "opencart.php", "prestashop.php", "woocommerce.php"},
//            {"dolphin.php", "elgg.php", "etano.php", "familyconnections.php", "gnu.php", "hubzilla.php", "humhub.php", "jcow.php", "opensource.php", "oxwall.php", "ph7cms.php", "una.php"},
//            {"clipbucket.php", "cumulusclips.php", "vidiscript.php"},
//            {"dokuwiki.php", "media-wiki-hosting.php", "pmwiki.php", "wikkawiki.php"},
//    };
//
//    public String[][] getFileNames() {
//        return this.fileNames;
//    }
//
//    public String[] getFolderNames() {
//        return this.folderNames;
//    }

//                        if(line.contains("img/icon/2/25.png")){
//                            String switched = line.replace("<?php echo Html::img('img/icon/2/25.png', ['lazy' => true, 'alt' => 'feature icon 1']); ?>", "<span class='sprite-icon-common-4 icon-upload-4'></span>");
//                            file.write(switched + "\n");
//                            switched = switched.replaceAll("shared-sprite-icon", " icon-wordpress");
//                            switched = switched.replaceAll("wordpress-sprite-icon", " icon-w");
//                            switched = switched.replaceAll("ssd-sprite-icon", " icon-ssd");
//                            switched = switched.replaceAll("cloud-sprite-icon", " icon-cloud");
//                            switched = switched.replaceAll("dedicated-sprite-icon", " icon-dedicated");
//                            switched = switched.replaceAll("domain-sprite-icon", " icon-domain");
//                            switched = switched.replaceAll("reseller-sprite-icon", " icon-reseller");
//                            switched = switched.replaceAll("ssl-sprite-icon", " icon-ssl");
//                            file.write(switched + "\n");
//                        }else {
//                            file.write(line);
//                            file.write("\n");
//                        }
