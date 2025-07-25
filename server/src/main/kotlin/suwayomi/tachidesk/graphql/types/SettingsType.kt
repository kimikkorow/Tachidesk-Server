/*
 * Copyright (C) Contributors to the Suwayomi project
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package suwayomi.tachidesk.graphql.types

import com.expediagroup.graphql.generator.annotations.GraphQLDeprecated
import org.jetbrains.exposed.sql.SortOrder
import suwayomi.tachidesk.graphql.server.primitives.Node
import suwayomi.tachidesk.server.ServerConfig
import suwayomi.tachidesk.server.serverConfig

interface Settings : Node {
    val ip: String?
    val port: Int?

    // proxy
    val socksProxyEnabled: Boolean?
    val socksProxyVersion: Int?
    val socksProxyHost: String?
    val socksProxyPort: String?
    val socksProxyUsername: String?
    val socksProxyPassword: String?

    // webUI
//    requires restart (found no way to mutate (serve + "unserve") served files during runtime), exclude for now
//    val webUIEnabled: Boolean,
    val webUIFlavor: WebUIFlavor?
    val initialOpenInBrowserEnabled: Boolean?
    val webUIInterface: WebUIInterface?
    val electronPath: String?
    val webUIChannel: WebUIChannel?
    val webUIUpdateCheckInterval: Double?

    // downloader
    val downloadAsCbz: Boolean?
    val downloadsPath: String?
    val autoDownloadNewChapters: Boolean?
    val excludeEntryWithUnreadChapters: Boolean?

    @GraphQLDeprecated(
        "Replaced with autoDownloadNewChaptersLimit",
        replaceWith = ReplaceWith("autoDownloadNewChaptersLimit"),
    )
    val autoDownloadAheadLimit: Int?
    val autoDownloadNewChaptersLimit: Int?
    val autoDownloadIgnoreReUploads: Boolean?
    val downloadConversions: List<SettingsDownloadConversion>?

    // extension
    val extensionRepos: List<String>?

    // requests
    val maxSourcesInParallel: Int?

    // updater
    val excludeUnreadChapters: Boolean?
    val excludeNotStarted: Boolean?
    val excludeCompleted: Boolean?
    val globalUpdateInterval: Double?
    val updateMangas: Boolean?

    // Authentication
    val authMode: AuthMode?
    val authUsername: String?
    val authPassword: String?

    @GraphQLDeprecated("Removed - prefer authMode")
    val basicAuthEnabled: Boolean?

    @GraphQLDeprecated("Removed - prefer authUsername")
    val basicAuthUsername: String?

    @GraphQLDeprecated("Removed - prefer authPassword")
    val basicAuthPassword: String?

    // misc
    val debugLogsEnabled: Boolean?

    @GraphQLDeprecated("Removed - does not do anything")
    val gqlDebugLogsEnabled: Boolean?
    val systemTrayEnabled: Boolean?
    val maxLogFiles: Int?
    val maxLogFileSize: String?
    val maxLogFolderSize: String?

    // backup
    val backupPath: String?
    val backupTime: String?
    val backupInterval: Int?
    val backupTTL: Int?

    // local source
    val localSourcePath: String?

    // cloudflare bypass
    val flareSolverrEnabled: Boolean?
    val flareSolverrUrl: String?
    val flareSolverrTimeout: Int?
    val flareSolverrSessionName: String?
    val flareSolverrSessionTtl: Int?
    val flareSolverrAsResponseFallback: Boolean?

    // opds
    val opdsUseBinaryFileSizes: Boolean?
    val opdsItemsPerPage: Int?
    val opdsEnablePageReadProgress: Boolean?
    val opdsMarkAsReadOnDownload: Boolean?
    val opdsShowOnlyUnreadChapters: Boolean?
    val opdsShowOnlyDownloadedChapters: Boolean?
    val opdsChapterSortOrder: SortOrder?
}

interface SettingsDownloadConversion {
    val mimeType: String
    val target: String
    val compressionLevel: Double?
}

class SettingsDownloadConversionType(
    override val mimeType: String,
    override val target: String,
    override val compressionLevel: Double?,
) : SettingsDownloadConversion

data class PartialSettingsType(
    override val ip: String?,
    override val port: Int?,
    // proxy
    override val socksProxyEnabled: Boolean?,
    override val socksProxyVersion: Int?,
    override val socksProxyHost: String?,
    override val socksProxyPort: String?,
    override val socksProxyUsername: String?,
    override val socksProxyPassword: String?,
    // webUI
    override val webUIFlavor: WebUIFlavor?,
    override val initialOpenInBrowserEnabled: Boolean?,
    override val webUIInterface: WebUIInterface?,
    override val electronPath: String?,
    override val webUIChannel: WebUIChannel?,
    override val webUIUpdateCheckInterval: Double?,
    // downloader
    override val downloadAsCbz: Boolean?,
    override val downloadsPath: String?,
    override val autoDownloadNewChapters: Boolean?,
    override val excludeEntryWithUnreadChapters: Boolean?,
    @GraphQLDeprecated(
        "Replaced with autoDownloadNewChaptersLimit",
        replaceWith = ReplaceWith("autoDownloadNewChaptersLimit"),
    )
    override val autoDownloadAheadLimit: Int?,
    override val autoDownloadNewChaptersLimit: Int?,
    override val autoDownloadIgnoreReUploads: Boolean?,
    override val downloadConversions: List<SettingsDownloadConversionType>?,
    // extension
    override val extensionRepos: List<String>?,
    // requests
    override val maxSourcesInParallel: Int?,
    // updater
    override val excludeUnreadChapters: Boolean?,
    override val excludeNotStarted: Boolean?,
    override val excludeCompleted: Boolean?,
    override val globalUpdateInterval: Double?,
    override val updateMangas: Boolean?,
    // Authentication
    override val authMode: AuthMode?,
    override val authUsername: String?,
    override val authPassword: String?,
    @GraphQLDeprecated("Removed - prefer authMode")
    override val basicAuthEnabled: Boolean?,
    @GraphQLDeprecated("Removed - prefer authUsername")
    override val basicAuthUsername: String?,
    @GraphQLDeprecated("Removed - prefer authPassword")
    override val basicAuthPassword: String?,
    // misc
    override val debugLogsEnabled: Boolean?,
    @GraphQLDeprecated("Removed - does not do anything")
    override val gqlDebugLogsEnabled: Boolean?,
    override val systemTrayEnabled: Boolean?,
    override val maxLogFiles: Int?,
    override val maxLogFileSize: String?,
    override val maxLogFolderSize: String?,
    // backup
    override val backupPath: String?,
    override val backupTime: String?,
    override val backupInterval: Int?,
    override val backupTTL: Int?,
    // local source
    override val localSourcePath: String?,
    // cloudflare bypass
    override val flareSolverrEnabled: Boolean?,
    override val flareSolverrUrl: String?,
    override val flareSolverrTimeout: Int?,
    override val flareSolverrSessionName: String?,
    override val flareSolverrSessionTtl: Int?,
    override val flareSolverrAsResponseFallback: Boolean?,
    // opds
    override val opdsUseBinaryFileSizes: Boolean?,
    override val opdsItemsPerPage: Int?,
    override val opdsEnablePageReadProgress: Boolean?,
    override val opdsMarkAsReadOnDownload: Boolean?,
    override val opdsShowOnlyUnreadChapters: Boolean?,
    override val opdsShowOnlyDownloadedChapters: Boolean?,
    override val opdsChapterSortOrder: SortOrder?,
) : Settings

class SettingsType(
    override val ip: String,
    override val port: Int,
    // proxy
    override val socksProxyEnabled: Boolean,
    override val socksProxyVersion: Int,
    override val socksProxyHost: String,
    override val socksProxyPort: String,
    override val socksProxyUsername: String,
    override val socksProxyPassword: String,
    // webUI
    override val webUIFlavor: WebUIFlavor,
    override val initialOpenInBrowserEnabled: Boolean,
    override val webUIInterface: WebUIInterface,
    override val electronPath: String,
    override val webUIChannel: WebUIChannel,
    override val webUIUpdateCheckInterval: Double,
    // downloader
    override val downloadAsCbz: Boolean,
    override val downloadsPath: String,
    override val autoDownloadNewChapters: Boolean,
    override val excludeEntryWithUnreadChapters: Boolean,
    @GraphQLDeprecated(
        "Replaced with autoDownloadNewChaptersLimit",
        replaceWith = ReplaceWith("autoDownloadNewChaptersLimit"),
    )
    override val autoDownloadAheadLimit: Int,
    override val autoDownloadNewChaptersLimit: Int,
    override val autoDownloadIgnoreReUploads: Boolean,
    override val downloadConversions: List<SettingsDownloadConversionType>,
    // extension
    override val extensionRepos: List<String>,
    // requests
    override val maxSourcesInParallel: Int,
    // updater
    override val excludeUnreadChapters: Boolean,
    override val excludeNotStarted: Boolean,
    override val excludeCompleted: Boolean,
    override val globalUpdateInterval: Double,
    override val updateMangas: Boolean,
    // Authentication
    override val authMode: AuthMode,
    override val authUsername: String,
    override val authPassword: String,
    @GraphQLDeprecated("Removed - prefer authMode")
    override val basicAuthEnabled: Boolean,
    @GraphQLDeprecated("Removed - prefer authUsername")
    override val basicAuthUsername: String,
    @GraphQLDeprecated("Removed - prefer authPassword")
    override val basicAuthPassword: String,
    // misc
    override val debugLogsEnabled: Boolean,
    @GraphQLDeprecated("Removed - does not do anything")
    override val gqlDebugLogsEnabled: Boolean,
    override val systemTrayEnabled: Boolean,
    override val maxLogFiles: Int,
    override val maxLogFileSize: String,
    override val maxLogFolderSize: String,
    // backup
    override val backupPath: String,
    override val backupTime: String,
    override val backupInterval: Int,
    override val backupTTL: Int,
    // local source
    override val localSourcePath: String,
    // cloudflare bypass
    override val flareSolverrEnabled: Boolean,
    override val flareSolverrUrl: String,
    override val flareSolverrTimeout: Int,
    override val flareSolverrSessionName: String,
    override val flareSolverrSessionTtl: Int,
    override val flareSolverrAsResponseFallback: Boolean,
    // opds
    override val opdsUseBinaryFileSizes: Boolean,
    override val opdsItemsPerPage: Int,
    override val opdsEnablePageReadProgress: Boolean,
    override val opdsMarkAsReadOnDownload: Boolean,
    override val opdsShowOnlyUnreadChapters: Boolean,
    override val opdsShowOnlyDownloadedChapters: Boolean,
    override val opdsChapterSortOrder: SortOrder,
) : Settings {
    constructor(config: ServerConfig = serverConfig) : this(
        config.ip.value,
        config.port.value,
        // proxy
        config.socksProxyEnabled.value,
        config.socksProxyVersion.value,
        config.socksProxyHost.value,
        config.socksProxyPort.value,
        config.socksProxyUsername.value,
        config.socksProxyPassword.value,
        // webUI
        config.webUIFlavor.value,
        config.initialOpenInBrowserEnabled.value,
        config.webUIInterface.value,
        config.electronPath.value,
        config.webUIChannel.value,
        config.webUIUpdateCheckInterval.value,
        // downloader
        config.downloadAsCbz.value,
        config.downloadsPath.value,
        config.autoDownloadNewChapters.value,
        config.excludeEntryWithUnreadChapters.value,
        config.autoDownloadNewChaptersLimit.value, // deprecated
        config.autoDownloadNewChaptersLimit.value,
        config.autoDownloadIgnoreReUploads.value,
        config.downloadConversions.value.map {
            SettingsDownloadConversionType(
                it.key,
                it.value.target,
                it.value.compressionLevel,
            )
        },
        // extension
        config.extensionRepos.value,
        // requests
        config.maxSourcesInParallel.value,
        // updater
        config.excludeUnreadChapters.value,
        config.excludeNotStarted.value,
        config.excludeCompleted.value,
        config.globalUpdateInterval.value,
        config.updateMangas.value,
        // Authentication
        config.authMode.value,
        config.authUsername.value,
        config.authPassword.value,
        config.basicAuthEnabled.value,
        config.basicAuthUsername.value,
        config.basicAuthPassword.value,
        // misc
        config.debugLogsEnabled.value,
        false,
        config.systemTrayEnabled.value,
        config.maxLogFiles.value,
        config.maxLogFileSize.value,
        config.maxLogFolderSize.value,
        // backup
        config.backupPath.value,
        config.backupTime.value,
        config.backupInterval.value,
        config.backupTTL.value,
        // local source
        config.localSourcePath.value,
        // cloudflare bypass
        config.flareSolverrEnabled.value,
        config.flareSolverrUrl.value,
        config.flareSolverrTimeout.value,
        config.flareSolverrSessionName.value,
        config.flareSolverrSessionTtl.value,
        config.flareSolverrAsResponseFallback.value,
        // opds
        config.opdsUseBinaryFileSizes.value,
        config.opdsItemsPerPage.value,
        config.opdsEnablePageReadProgress.value,
        config.opdsMarkAsReadOnDownload.value,
        config.opdsShowOnlyUnreadChapters.value,
        config.opdsShowOnlyDownloadedChapters.value,
        config.opdsChapterSortOrder.value,
    )
}
