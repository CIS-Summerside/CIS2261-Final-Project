﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WindowsClient.Api {
    using System;
    
    
    /// <summary>
    ///   A strongly-typed resource class, for looking up localized strings, etc.
    /// </summary>
    // This class was auto-generated by the StronglyTypedResourceBuilder
    // class via a tool like ResGen or Visual Studio.
    // To add or remove a member, edit your .ResX file then rerun ResGen
    // with the /str option, or rebuild your VS project.
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("System.Resources.Tools.StronglyTypedResourceBuilder", "4.0.0.0")]
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
    [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
    internal class EndpointRefs {
        
        private static global::System.Resources.ResourceManager resourceMan;
        
        private static global::System.Globalization.CultureInfo resourceCulture;
        
        [global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
        internal EndpointRefs() {
        }
        
        /// <summary>
        ///   Returns the cached ResourceManager instance used by this class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        internal static global::System.Resources.ResourceManager ResourceManager {
            get {
                if (object.ReferenceEquals(resourceMan, null)) {
                    global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("WindowsClient.Api.EndpointRefs", typeof(EndpointRefs).Assembly);
                    resourceMan = temp;
                }
                return resourceMan;
            }
        }
        
        /// <summary>
        ///   Overrides the current thread's CurrentUICulture property for all
        ///   resource lookups using this strongly typed resource class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        internal static global::System.Globalization.CultureInfo Culture {
            get {
                return resourceCulture;
            }
            set {
                resourceCulture = value;
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to http://tfhs.ddns.net:8080/api/metrics/computer/add.
        /// </summary>
        internal static string computerUrl {
            get {
                return ResourceManager.GetString("computerUrl", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to http://tfhs.ddns.net:8080/api/files/download/.
        /// </summary>
        internal static string downloadURL {
            get {
                return ResourceManager.GetString("downloadURL", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to http://tfhs.ddns.net:8080/api/user/login.
        /// </summary>
        internal static string loginURL {
            get {
                return ResourceManager.GetString("loginURL", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to http://tfhs.ddns.net:8080/api/user/logout.
        /// </summary>
        internal static string logoutURL {
            get {
                return ResourceManager.GetString("logoutURL", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to http://tfhs.ddns.net:8080/api/user/add.
        /// </summary>
        internal static string registerURL {
            get {
                return ResourceManager.GetString("registerURL", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to http://tfhs.ddns.net:8080/api/files/upload.
        /// </summary>
        internal static string uploadURL {
            get {
                return ResourceManager.GetString("uploadURL", resourceCulture);
            }
        }
    }
}
